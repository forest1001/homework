export class Transaction {
    lineNumber: number | undefined;
    isin: string | undefined;
    issuer: string | undefined;
    applicationType: string | undefined;
    instrumentType: string | undefined;
    listingDate: string | undefined;
    validationErrors: string[] | undefined;
}
