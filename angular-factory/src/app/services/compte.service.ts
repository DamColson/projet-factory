import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from '../model/compte';

@Injectable({
  providedIn: 'root',
})
export class CompteService {
  constructor(private http: HttpClient) {}

  public inscription(obj: any): Observable<Compte> {
    return this.http.post(
      'http://localhost:8080/factory/compte/inscription',
      obj
    );
  }
}
