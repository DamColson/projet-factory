import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Competence } from '../model/competence';

@Injectable({
  providedIn: 'root',
})
export class CompetenceService {
  url = 'http://localhost:8080/factory/api/competence';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Competence[]> {
    return this.httpClient.get<Competence[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(competence: any): Observable<Competence> {
    return this.httpClient.post<any>(this.url, competence);
  }

  public getById(id: number): Observable<Competence> {
    return this.httpClient.get<Competence>(`${this.url}/${id}`);
  }

  public update(competence: any): Observable<Competence> {
    return this.httpClient.put<any>(`${this.url}/${competence.id}`, competence);
  }

  private competenceToCompetenceRequest(competence: Competence): any {
    let obj = {
      id: competence.id,
      nom: competence.nom,
    };
    return obj;
  }
}
