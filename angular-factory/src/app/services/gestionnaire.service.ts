import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gestionnaire } from '../model/gestionnaire';

@Injectable({
  providedIn: 'root',
})
export class GestionnaireService {
  url = 'http://localhost:8080/factory/api/gestionnaire';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Gestionnaire[]> {
    return this.httpClient.get<Gestionnaire[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(gestionnaire: Gestionnaire): Observable<Gestionnaire> {
    return this.httpClient.post<Gestionnaire>(
      this.url,
      this.gestionnaireToGestionnaireRequest(gestionnaire)
    );
  }

  public getById(id: number): Observable<Gestionnaire> {
    return this.httpClient.get<Gestionnaire>(`${this.url}/${id}`);
  }

  public update(gestionnaire: Gestionnaire): Observable<Gestionnaire> {
    return this.httpClient.put<Gestionnaire>(
      `${this.url}/${gestionnaire.id}`,
      this.gestionnaireToGestionnaireRequest(gestionnaire)
    );
  }

  private gestionnaireToGestionnaireRequest(gestionnaire: Gestionnaire): any {
    let obj = {
      id: gestionnaire.id,
      nom: gestionnaire.nom,
      prenom: gestionnaire.prenom,
      mail: gestionnaire.mail,
      telephone: gestionnaire.telephone,
      idCompte: gestionnaire.compteResponse?.id,
      idOrdinateur: gestionnaire.ordinateurResponse?.id,
      formations: gestionnaire.formationsResponse,
    };
    return obj;
  }
}
