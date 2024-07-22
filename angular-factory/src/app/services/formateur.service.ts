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

  public update(formateur: Formateur): Observable<Formateur> {
    return this.httpClient.put<Formateur>(
      `${this.url}/${formateur.id}`,
      this.formateurToFormateurRequest(formateur)
    );
  }

  private formateurToFormateurRequest(formateur: Formateur): any {
    let obj = {
      id: formateur.id,
      nom: formateur.nom,
      prenom: formateur.prenom,
      mail: formateur.mail,
      telephone: formateur.telephone,
      idCompte: formateur.compteResponse?.id,
      idOrdinateur: formateur.ordinateurResponse?.id,
      idVideoProjecteur: formateur.videoprojecteurResponse?.id,
      idFormation: formateur.videoprojecteurResponse?.id,
    };
    return obj;
  }
}
