import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Stagiaire } from '../model/stagiaire';

@Injectable({
  providedIn: 'root',
})
export class StagiaireService {
  url = 'http://localhost:8080/factory/api/stagiaire';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Stagiaire[]> {
    return this.httpClient.get<Stagiaire[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(stagiaire: Stagiaire): Observable<Stagiaire> {
    return this.httpClient.post<Stagiaire>(
      this.url,
      this.stagiaireToStagiaireRequest(stagiaire)
    );
  }

  public getById(id: number): Observable<Stagiaire> {
    return this.httpClient.get<Stagiaire>(`${this.url}/${id}`);
  }

  public update(stagiaire: Stagiaire): Observable<Stagiaire> {
    return this.httpClient.put<Stagiaire>(
      `${this.url}/${stagiaire.id}`,
      this.stagiaireToStagiaireRequest(stagiaire)
    );
  }

  private stagiaireToStagiaireRequest(stagiaire: Stagiaire): any {
    let obj = {
      id: stagiaire.id,
      nom: stagiaire.nom,
      prenom: stagiaire.prenom,
      mail: stagiaire.mail,
      telephone: stagiaire.telephone,
      idCompte: stagiaire.compteResponse?.id,
      idOrdinateur: stagiaire.ordinateurResponse?.id,
      idFormation: stagiaire.formationResponse?.id,
    };
    return obj;
  }
}
