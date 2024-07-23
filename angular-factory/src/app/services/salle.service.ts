import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ordinateur } from '../model/ordinateur';
import { Salle } from '../model/salle';

@Injectable({
  providedIn: 'root',
})
export class SalleService {
  url = 'http://localhost:8080/factory/api/salle';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Salle[]> {
    return this.httpClient.get<Salle[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(salle: Salle): Observable<Salle> {
    return this.httpClient.post<Salle>(
      this.url,
      this.salleToSalleRequest(salle)
    );
  }

  public getById(id: number): Observable<Salle> {
    return this.httpClient.get<Salle>(`${this.url}/${id}`);
  }

  public update(salle: Salle): Observable<Salle> {
    return this.httpClient.put<Salle>(
      `${this.url}/${salle.id}`,
      this.salleToSalleRequest(salle)
    );
  }

  private salleToSalleRequest(salle: Salle): any {
    let obj = {
      id: salle.id,
      superficie: salle.superficie,
      libelle: salle.libelle,
      ordinateur: salle.ordinateursResponse,
      videoprojecteur: salle.videoprojecteurResponse?.id,
    };
    return obj;
  }
}
