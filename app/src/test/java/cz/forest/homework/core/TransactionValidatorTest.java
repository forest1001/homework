package cz.forest.homework.core;

import cz.forest.homework.HomeworkApplication;
import cz.forest.homework.config.ValidationMessages;
import cz.forest.homework.model.TransactionCsvLine;
import cz.forest.homework.service.TransactionValidatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HomeworkApplication.class)
public class TransactionValidatorTest {

    private static final String ISIN_VALID = "DE0001104842";
    private static final String LISTING_DATE_VALID = "6.6.2020";

    @Autowired
    private TransactionValidatorService service;
    @Autowired
    private ValidationMessages validationMessages;

    @Test
    public void testValid() {
        List<String> errors = service.validate(createDto(ISIN_VALID, LISTING_DATE_VALID));
        Assert.assertEquals("Should be is valid", 0, errors.size());
    }

    @Test
    public void testIsin() {
        for (String isin : new String[]{null, "", "DE00011048422", "DE000110484", "XX0001104842", "110001104842", "DE000110484D"}) {
            List<String> errors = service.validate(createDto(isin, LISTING_DATE_VALID));
            Assert.assertTrue(String.format("ISIN %s is invalid.", isin), errors.contains(validationMessages.getIsinFormat()));
            Assert.assertEquals(1, errors.size());
        }
    }

    @Test
    public void testListingDate() {
        for (String date : new String[]{"02.66.2019", "asdfa", "32.1.2020", "1.1.-2020"}) {
            List<String> errors = service.validate(createDto(ISIN_VALID, date));
            Assert.assertTrue(String.format("date %s is invalid.", date), errors.contains(validationMessages.getListingDateFormat()));
            Assert.assertEquals(1, errors.size());
        }
    }

    private TransactionCsvLine createDto(String isin, String listingDate) {
        TransactionCsvLine dto = new TransactionCsvLine();
        dto.setIsin(isin);
        dto.setInstrumentType("type");
        dto.setApplicationType("application");
        dto.setIssuer("issuer");
        dto.setListingDate(listingDate);
        return dto;
    }
}
