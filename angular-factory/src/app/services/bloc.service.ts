import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ordinateur } from '../model/ordinateur';
import { Bloc } from '../model/bloc';

@Injectable({
  providedIn: 'root',
})
export class BlocService {
  url = 'http://localhost:8080/factory/api/bloc';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Bloc[]> {
    return this.httpClient.get<Bloc[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(bloc: Bloc): Observable<Bloc> {
    return this.httpClient.post<Bloc>(this.url, this.blocToBlocRequest(bloc));
  }

  public getById(id: number): Observable<Bloc> {
    return this.httpClient.get<Bloc>(`${this.url}/${id}`);
  }

  public update(bloc: Bloc): Observable<Bloc> {
    return this.httpClient.put<Bloc>(
      `${this.url}/${bloc.id}`,
      this.blocToBlocRequest(bloc)
    );
  }

  private blocToBlocRequest(bloc: Bloc): any {
    let obj = {
      id: bloc.id,
      dateDebut: bloc.dateDebut,
      dateFin: bloc.dateFin,
      matiere: bloc.matiereResponse?.id,
      salle: bloc.salleResponse?.id,
      formateur: bloc.formateurResponse?.id,
      code: bloc.code,
      objectif: bloc.objectif,
    };
    return obj;
  }
}
