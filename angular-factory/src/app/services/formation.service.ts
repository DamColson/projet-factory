import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Formation } from '../model/formation';
import { Form } from '@angular/forms';
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
    let obj = {
      id: formation.id,
      debut_formation: formation.debut_formation,
      fin_formation: formation.fin_formation,
      gesitonnaire_id: formation.gestionnaire_id,
      prerequis: formation.prerequis,
    };
    return obj;
  }
}