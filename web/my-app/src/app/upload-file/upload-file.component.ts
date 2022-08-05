import {Component, forwardRef, OnInit, ViewChild} from '@angular/core';
import {UploadFileService} from "./upload-file.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort, Sort} from '@angular/material/sort';
import {Transaction} from "../model/transaction";
import {TransactionError} from "../model/transaction-error";

@Component({
    selector: 'app-upload-file',
    templateUrl: './upload-file.component.html',
    styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

    serverErr!: string;
    file!: File;
    displayedColumns: string[] = ['isin', 'issuer', 'applicationType', 'instrumentType', 'listingDate'];
    displayedColumnsErr: string[] = ['lineNumber', 'validationErrors'];
    uploadFileService: UploadFileService;
    dataSource!: MatTableDataSource<Transaction>;
    dataSourceError!: MatTableDataSource<TransactionError>;
    showInvalid: boolean = false;
    @ViewChild(MatSort) sort!: MatSort;

    constructor(uploadFileService: UploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    ngOnInit(): void {
        this.dataSource = new MatTableDataSource<Transaction>([]);
        this.dataSourceError = new MatTableDataSource<TransactionError>([]);
    }

    onChange(event: Event) {
        const target = event.target as HTMLInputElement;
        this.file = target.files![0];
    }

    onUpload() {
        this.uploadFileService.upload(this.file).subscribe(
            (eventList: any) => {
                this.dataSource = new MatTableDataSource<Transaction>([]);
                this.dataSourceError = new MatTableDataSource<TransactionError>([]);

                for (let i = 0; i < eventList.length; i++) {
                    let event: Transaction = eventList[i];
                    if (event.validationErrors?.length) {
                        this.dataSourceError.data.push(new TransactionError(event.lineNumber, event.validationErrors));
                    } else {
                        this.dataSource.data.push(event);
                    }
                }
                this.dataSource.filterPredicate = this.createIsinFilter();
                this.dataSource.sort = this.sort;
                this.serverErr = "";
            }, (event: any) => {
                this.serverErr = "HTTP " + event.error.code + ". " + event.error.message;
                this.dataSource = new MatTableDataSource<Transaction>([]);
                this.dataSourceError = new MatTableDataSource<TransactionError>([]);
        });
    }

    createIsinFilter() {
        return function (data: Transaction, filter: string) {
            return data.isin !== null && data.isin?.toLowerCase().indexOf(filter.toLowerCase()) !== -1;
        };
    }

    applyFilter(event: Event) {
        const filterValue = (event.target as HTMLInputElement).value;
        this.dataSource.filter = filterValue.trim().toLowerCase();
    }
}

