import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ordinateur } from '../model/ordinateur';
import { Videoprojecteur } from '../model/videoprojecteur';

@Injectable({
  providedIn: 'root',
})
export class VideoprojecteurService {
  url = 'http://localhost:8080/factory/api/videoprojecteur';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Videoprojecteur[]> {
    return this.httpClient.get<Videoprojecteur[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(videoprojecteur: Videoprojecteur): Observable<Videoprojecteur> {
    return this.httpClient.post<Videoprojecteur>(
      this.url,
      this.videoprojecteurToVideoprojecteurrRequest(videoprojecteur)
    );
  }

  public getById(id: number): Observable<Videoprojecteur> {
    return this.httpClient.get<Videoprojecteur>(`${this.url}/${id}`);
  }

  public update(videoprojecteur: Videoprojecteur): Observable<Videoprojecteur> {
    return this.httpClient.put<Videoprojecteur>(
      `${this.url}/${videoprojecteur.id}`,
      this.videoprojecteurToVideoprojecteurrRequest(videoprojecteur)
    );
  }

  private videoprojecteurToVideoprojecteurrRequest(
    ordinateur: Ordinateur
  ): any {
    let obj = {
      id: ordinateur.id,
      adresse_mac: ordinateur.adresse_mac,
      date_achat: ordinateur.date_achat,
      libelle: ordinateur.libelle,
      Status: ordinateur.status,
    };
    return obj;
  }
}
