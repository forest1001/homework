export class TransactionError {
    lineNumber: number | undefined;
    validationErrors: string[] | undefined;

    constructor(lineNumber: number | undefined, validationErrors: string[]) {
        this.lineNumber = lineNumber;
        this.validationErrors = validationErrors;
    }
}
