package cz.forest.homework.model;

import java.util.List;

public class TransactionDto {

    private int lineNumber;
    private String isin;
    private String issuer;
    private String applicationType;
    private String instrumentType;
    private String listingDate;
    private List<String> validationErrors;


    public TransactionDto(int lineNumber, TransactionCsvLine line, List<String> validationErrors) {
        this.lineNumber = lineNumber;
        this.isin = line == null ? null : line.getIsin();
        this.issuer = line == null ? null : line.getIssuer();
        this.applicationType = line == null ? null : line.getApplicationType();
        this.instrumentType = line == null ? null : line.getApplicationType();
        this.listingDate = line == null ? null : line.getListingDate();
        this.validationErrors = validationErrors;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
