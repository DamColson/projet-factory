import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formateur } from '../model/formateur';

@Injectable({
  providedIn: 'root',
})
export class FormateurService {
  url = 'http://localhost:8080/factory/api/formateur';

  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Formateur[]> {
    return this.httpClient.get<Formateur[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(formateur: Formateur): Observable<Formateur> {
    return this.httpClient.post<Formateur>(
      this.url,
      this.formateurToFormateurRequest(formateur)
    );
  }

  public getById(id: number): Observable<Formateur> {
    return this.httpClient.get<Formateur>(`${this.url}/${id}`);
  }

  public update(formateur: Formateur): Observable<any> {
    return this.httpClient.put<any>(
      `${this.url}/${formateur.id}`,
      this.formateurToFormateurRequest(formateur)
    );
  }

  private formateurToFormateurRequest(formateur: Formateur): any {
    let competencesId: number[] = [];
    formateur.competencesResponse?.forEach((competence) =>
      competencesId.push(competence.id!)
    );

    console.log(competencesId);

    let obj = {
      nom: formateur.nom,
      prenom: formateur.prenom,
      telephone: formateur.telephone,
      mail: formateur.mail,
      compteId: formateur.compteResponse?.id,
      ordinateurId: formateur.ordinateurResponse?.id,
      videoProjecteurId: formateur.videoProjecteurResponse?.id,
      competencesId: competencesId,
    };
    return obj;
  }
}
