import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from '../model/compte';

@Injectable({
  providedIn: 'root',
})
export class CompteService {
  url = 'http://localhost:8080/factory/api/compte';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Compte[]> {
    return this.httpClient.get<Compte[]>(this.url);
  }

  public getAllStag(): Observable<Compte[]> {
    return this.httpClient.get<Compte[]>(this.url + '/free-stag');
  }
  public getAllForm(): Observable<Compte[]> {
    return this.httpClient.get<Compte[]>(this.url + '/free-form');
  }
  public getAllTech(): Observable<Compte[]> {
    return this.httpClient.get<Compte[]>(this.url + '/free-tech');
  }

  public getAllGest(): Observable<Compte[]> {
    return this.httpClient.get<Compte[]>(this.url + '/free-gest');
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(compte: any): Observable<Compte> {
    return this.httpClient.post<any>(this.url, compte);
  }

  public getById(id: number): Observable<Compte> {
    return this.httpClient.get<Compte>(`${this.url}/${id}`);
  }

  public update(compte: any): Observable<Compte> {
    console.log(compte);
    return this.httpClient.put<any>(`${this.url}/${compte.id}`, compte);
  }

  private compteToCompteRequest(compte: Compte): any {
    let obj = {
      id: compte.id,
      login: compte.login,
      role: compte.role,
    };
    return obj;
  }
}
