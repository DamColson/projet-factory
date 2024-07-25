import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Matiere } from '../model/matiere';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MatiereService {
  url = 'http://localhost:8080/factory/api/matiere';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Matiere[]> {
    return this.httpClient.get<Matiere[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(matiere: Matiere): Observable<Matiere> {
    return this.httpClient.post<Matiere>(
      this.url,
      this.matiereToMatiereRequest(matiere)
    );
  }

  public getById(id: number): Observable<Matiere> {
    return this.httpClient.get<Matiere>(`${this.url}/${id}`);
  }

  public update(matiere: Matiere): Observable<Matiere> {
    return this.httpClient.put<Matiere>(
      `${this.url}/${matiere.id}`,
      this.matiereToMatiereRequest(matiere)
    );
  }

  private matiereToMatiereRequest(matiere: Matiere): any {
    let competencesId: number[] = [];
    matiere.competencesResponse?.forEach((competence) =>
      competencesId.push(competence.id!)
    );
    let obj = {
      id: matiere.id,
      titre: matiere.titre,
      contenu: matiere.contenu,
      competencesId: competencesId,
    };
    return obj;
  }
}
