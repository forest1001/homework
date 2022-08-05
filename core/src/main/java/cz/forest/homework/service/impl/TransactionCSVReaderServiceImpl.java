package cz.forest.homework.service.impl;

import cz.forest.homework.config.ValidationMessages;
import cz.forest.homework.exception.HomeworkBaseRuntimeException;
import cz.forest.homework.model.TransactionCsvLine;
import cz.forest.homework.model.TransactionDto;
import cz.forest.homework.service.TransactionCSVReaderService;
import cz.forest.homework.service.TransactionValidatorService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class TransactionCSVReaderServiceImpl implements TransactionCSVReaderService {

    private final String SEP = ";";
    private final String EXPECTED_HEADER = "ISIN;Issuer;Type of application;Type of instrument;Listing date";
    private final int EXPECTED_COLUMNS = EXPECTED_HEADER.split(SEP).length;
    private static final String UTF8_BYTE_ORDER_MARK = "\uFEFF";

    private final ValidationMessages validationMessages;
    private final TransactionValidatorService validatorService;


    public TransactionCSVReaderServiceImpl(ValidationMessages validationMessages, TransactionValidatorService validatorService) {
        this.validationMessages = validationMessages;
        this.validatorService = validatorService;
    }

    @Override
    public List<TransactionDto> readCsvFile(byte[] bytes) {
        List<TransactionDto> csvList = new ArrayList<>();

        // <ISIN-ListingDate, List<line numbers>>
        Map<String, List<Integer>> duplicityMapCheck = new HashMap<>();
        Set<Integer> isinDateDuplicityLines = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)))) {
            // process header line
            String line = br.readLine().trim();
            line = line.startsWith(UTF8_BYTE_ORDER_MARK) ? line.substring(1) : line;
            if (!EXPECTED_HEADER.equals(line) ) {
                throw new HomeworkBaseRuntimeException("File does not appear to have valid csv structure!");
            }

            int lineNum = 1;
            while((line = br.readLine()) != null) {
                String[] splits = line.split(SEP, -1);

                if (splits.length == EXPECTED_COLUMNS) {
                    // basic validation
                    TransactionCsvLine csvLine = new TransactionCsvLine(splits[0], splits[1], splits[2], splits[3], splits[4]);
                    List<String> errors = validatorService.validate(csvLine);
                    csvList.add(new TransactionDto(lineNum, csvLine, errors));

                    // check for ISIN - Listing date duplicity
                    List<Integer> lines = duplicityMapCheck.computeIfAbsent(String.format("%s-%s", csvLine.getIsin(), csvLine.getListingDate()), a -> new ArrayList<>());
                    lines.add(lineNum);
                    if (lines.size() > 1) {
                        isinDateDuplicityLines.addAll(lines);
                    }
                } else {
                    // Not easy to guess which column is missing
                    csvList.add(new TransactionDto(lineNum, null, List.of(validationMessages.getGenericIncorrectColumnCount() + " - " + line)));
                }
                lineNum++;
            }

        } catch (IOException e) {
            throw new HomeworkBaseRuntimeException("Failed processing CSV file", e);
        }

        for (Integer lineNum : isinDateDuplicityLines) {
            csvList.get(lineNum - 1).getValidationErrors().add(validationMessages.getCombinationIsinDate());
        }

        return csvList;
    }
}
