import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Compte } from '../model/compte';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private httpClient: HttpClient) {}

  public connect(login: string, password: string): Observable<Compte> {
    let auth = 'Basic ' + window.btoa(login + ':' + password);
    return this.httpClient.get<Compte>(
      'http://localhost:8080/factory/api/auth',
      {
        headers: { Authorization: auth },
      }
    );
  }
}
