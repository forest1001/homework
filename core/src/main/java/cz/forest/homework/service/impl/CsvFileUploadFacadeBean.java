package cz.forest.homework.service.impl;

import cz.forest.homework.model.TransactionDto;
import cz.forest.homework.service.CsvFileUploadFacade;
import cz.forest.homework.service.TransactionCSVReaderService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvFileUploadFacadeBean implements CsvFileUploadFacade {

    private TransactionCSVReaderService readerService;

    public CsvFileUploadFacadeBean(TransactionCSVReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    public List<TransactionDto> processCsvUpload(byte[] bytes, String fileName) {
        return readerService.readCsvFile(bytes);
    }
}
