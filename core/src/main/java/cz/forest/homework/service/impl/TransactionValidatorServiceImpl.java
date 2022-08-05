package cz.forest.homework.service.impl;

import cz.forest.homework.config.ValidationMessages;
import cz.forest.homework.model.TransactionCsvLine;
import cz.forest.homework.service.TransactionValidatorService;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class TransactionValidatorServiceImpl implements TransactionValidatorService {

    private static final int ISIN_LENGTH = 12;
    private static final Set<String> ISO_COUNTRY_CODES = new HashSet<>(Arrays.asList(Locale.getISOCountries()));
    private static final Pattern ISIN_SUFF_PATTERN = Pattern.compile("[a-zA-Z0-9]{9}\\d");
    private static final DateTimeFormatter LISTING_DATE_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    private final ValidationMessages validationMessages;

    public TransactionValidatorServiceImpl(ValidationMessages validationMessages) {
        this.validationMessages = validationMessages;
    }

    @Override
    public List<String> validate(TransactionCsvLine dto) {
        ArrayList<String> errors = new ArrayList<>();
        if (!isinValid(dto.getIsin())) {
            validationMessages.getIsinFormat();
            errors.add(validationMessages.getIsinFormat());
        }
        if (!issuerValid(dto.getIssuer())) {
            errors.add(validationMessages.getIssuerEmpty());
        }
        if (!applicationTypeValid(dto.getApplicationType())) {
            errors.add(validationMessages.getApplicationTypeEmpty());
        }
        if (!instrumentTypeValid(dto.getInstrumentType())) {
            errors.add(validationMessages.getInstrumentTypeEmpty());
        }
        if (!listingDateValid(dto.getListingDate())) {
            errors.add(validationMessages.getListingDateFormat());
        }
        return errors;
    }

    /*
    (Wikipedia.org) ISINs consist of
    - two alphabetic characters, which are the ISO 3166-1 alpha-2 code for the issuing country,
    - nine alpha-numeric characters (the National Securities Identifying Number, or NSIN,which identifies the security, padded as necessary with leading zeros),
    - and one numerical check digit.
    They are thus always 12 characters in length.
    */
    private boolean isinValid(String isin) {
        return isin != null && isin.length() == ISIN_LENGTH && ISO_COUNTRY_CODES.contains(isin.substring(0, 2))
                && ISIN_SUFF_PATTERN.matcher(isin.substring(2)).matches();
    }


    private boolean instrumentTypeValid(String instrumentType) {
        return instrumentType != null &&  !instrumentType.isEmpty();
    }

    private boolean applicationTypeValid(String applicationType) {
        return applicationType != null && !applicationType.isEmpty();
    }

    private boolean issuerValid(String issuer) {
        return issuer != null && !issuer.isEmpty();
    }

    private boolean listingDateValid(String listingDate) {
        if (listingDate == null || listingDate.isEmpty()) {
            return true;
        }
        try {
            LISTING_DATE_FORMATTER.parse(listingDate);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

}
