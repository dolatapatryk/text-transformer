import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Option } from './option-model';

@Injectable({
  providedIn: 'root'
})
export class OptionsService {

  private optionsUrl = '/options/';

  constructor(private http: HttpClient) { }

  getOptions() {
    return this.http.get<Option[]>(this.optionsUrl);
  }
}
