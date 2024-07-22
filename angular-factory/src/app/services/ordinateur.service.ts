import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ordinateur } from '../model/ordinateur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrdinateurService {
  url = 'http://localhost:8080/factory/api/ordinateur';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Ordinateur[]> {
    return this.httpClient.get<Ordinateur[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(ordinateur: Ordinateur): Observable<Ordinateur> {
    return this.httpClient.post<Ordinateur>(
      this.url,
      this.ordinateurToOrdinateurRequest(ordinateur)
    );
  }

  public getById(id: number): Observable<Ordinateur> {
    return this.httpClient.get<Ordinateur>(`${this.url}/${id}`);
  }

  public update(ordinateur: Ordinateur): Observable<Ordinateur> {
    return this.httpClient.put<Ordinateur>(
      `${this.url}/${ordinateur.id}`,
      this.ordinateurToOrdinateurRequest(ordinateur)
    );
  }

  private ordinateurToOrdinateurRequest(ordinateur: Ordinateur): any {
    let obj = {
      id: ordinateur.id,
      adresse_mac: ordinateur.adresse_mac,
      date_achat: ordinateur.date_achat,
      libelle: ordinateur.libelle,
      OS: ordinateur.os,
      Status: ordinateur.status,
    };
    return obj;
  }
}