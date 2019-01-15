import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { UserTransform } from './user-transform-model';
@Injectable({
  providedIn: 'root'
})
export class OwnTransformationService {

  private url = '/create_transformation/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getUserTransforms(): Observable<UserTransform[]> {
    return this.http.get<UserTransform[]>(this.url);
  }

  addUserTransform(input: Object) {
    return this.http.post(this.url, input, this.httpOptions);
  }

  updateUserTransform(name: string, transforms: string[]) {
    return this.http.put(this.url + name, transforms, this.httpOptions);
  }
}
