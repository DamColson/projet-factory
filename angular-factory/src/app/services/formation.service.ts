import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Formation } from '../model/formation';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormationService {
  url = 'http://localhost:8080/factory/api/formation';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Formation[]> {
    return this.httpClient.get<Formation[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(formation: Formation): Observable<Formation> {
    return this.httpClient.post<Formation>(
      this.url,
      this.formationToFormationRequest(formation)
    );
  }

  public getById(id: number): Observable<Formation> {
    return this.httpClient.get<Formation>(`${this.url}/${id}`);
  }

  public update(formation: Formation): Observable<Formation> {
    return this.httpClient.put<Formation>(
      `${this.url}/${formation.id}`,
      this.formationToFormationRequest(formation)
    );
  }

  private formationToFormationRequest(formation: Formation): any {
    let blocsId: number[] = [];
    formation.blocsResponse?.forEach((bloc) => blocsId.push(bloc.id!));
    let obj = {
      id: formation.id,
      libelle: formation.libelle,
      debut: formation.debut,
      fin: formation.fin,
      gestionnaireId: formation.gestionnaireResponse?.id,
      prerequis: formation.prerequis,
      stagiaires: formation.stagiairesResponse,
      blocsId: blocsId,
    };
    return obj;
  }
}
