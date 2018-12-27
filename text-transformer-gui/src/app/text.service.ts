import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Text } from './text-model';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TextService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  private textUrl = '/json/'

  constructor(private http: HttpClient) { }

  transformText(input: Object): Observable<Text> {
    return this.http.post<Text>(this.textUrl, input, this.httpOptions);
  }
}
