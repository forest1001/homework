package cz.forest.homework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error")
public class ValidationMessages {

    private String isinFormat;
    private String issuerEmpty;
    private String applicationTypeEmpty;
    private String instrumentTypeEmpty;
    private String listingDateFormat;
    private String combinationIsinDate;
    private String genericIncorrectColumnCount;

    public String getIsinFormat() {
        return isinFormat;
    }

    public void setIsinFormat(String isinFormat) {
        this.isinFormat = isinFormat;
    }

    public String getIssuerEmpty() {
        return issuerEmpty;
    }

    public void setIssuerEmpty(String issuerEmpty) {
        this.issuerEmpty = issuerEmpty;
    }

    public String getApplicationTypeEmpty() {
        return applicationTypeEmpty;
    }

    public void setApplicationTypeEmpty(String applicationTypeEmpty) {
        this.applicationTypeEmpty = applicationTypeEmpty;
    }

    public String getInstrumentTypeEmpty() {
        return instrumentTypeEmpty;
    }

    public void setInstrumentTypeEmpty(String instrumentTypeEmpty) {
        this.instrumentTypeEmpty = instrumentTypeEmpty;
    }

    public String getListingDateFormat() {
        return listingDateFormat;
    }

    public void setListingDateFormat(String listingDateFormat) {
        this.listingDateFormat = listingDateFormat;
    }

    public String getCombinationIsinDate() {
        return combinationIsinDate;
    }

    public void setCombinationIsinDate(String combinationIsinDate) {
        this.combinationIsinDate = combinationIsinDate;
    }

    public String getGenericIncorrectColumnCount() {
        return genericIncorrectColumnCount;
    }

    public void setGenericIncorrectColumnCount(String genericIncorrectColumnCount) {
        this.genericIncorrectColumnCount = genericIncorrectColumnCount;
    }
}
