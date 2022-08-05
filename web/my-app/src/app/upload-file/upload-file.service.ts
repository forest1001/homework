import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  private uploadUrl: string;

  constructor(private http: HttpClient) {
    this.uploadUrl = 'http://localhost:8080/upload';
  }

  upload(file: File):Observable<any> {
    const formData = new FormData();
    formData.append("file", file, file.name);
    return this.http.post(this.uploadUrl, formData)
  }
}
