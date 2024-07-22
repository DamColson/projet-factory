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

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(compte: Compte): Observable<Compte> {
    return this.httpClient.post<Compte>(
      this.url,
      this.compteToCompteRequest(compte)
    );
  }

  public getById(id: number): Observable<Compte> {
    return this.httpClient.get<Compte>(`${this.url}/${id}`);
  }

  public update(compte: Compte): Observable<Compte> {
    return this.httpClient.put<Compte>(
      `${this.url}/${compte.id}`,
      this.compteToCompteRequest(compte)
    );
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
