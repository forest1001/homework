package cz.forest.homework.model;

public class TransactionCsvLine {

    private String isin;
    private String issuer;
    private String applicationType;
    private String instrumentType;
    private String listingDate;

    public TransactionCsvLine() {
    }

    public TransactionCsvLine(String isin, String issuer, String applicationType, String instrumentType, String listingDate) {
        this.isin = isin;
        this.issuer = issuer;
        this.applicationType = applicationType;
        this.instrumentType = instrumentType;
        this.listingDate = listingDate;
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
}
