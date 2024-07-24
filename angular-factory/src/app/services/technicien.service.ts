import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Technicien } from '../model/technicien';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TechnicienService {
  url = 'http://localhost:8080/factory/api/technicien';
  constructor(private httpClient: HttpClient) {}

  public getAll(): Observable<Technicien[]> {
    return this.httpClient.get<Technicien[]>(this.url);
  }

  public delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.url}/${id}`);
  }

  public create(technicien: Technicien): Observable<Technicien> {
    return this.httpClient.post<Technicien>(
      this.url,
      this.technicienToTechnicienRequest(technicien)
    );
  }

  public getById(id: number): Observable<Technicien> {
    return this.httpClient.get<Technicien>(`${this.url}/${id}`);
  }

  public update(technicien: Technicien): Observable<Technicien> {
    return this.httpClient.put<Technicien>(
      `${this.url}/${technicien.id}`,
      this.technicienToTechnicienRequest(technicien)
    );
  }

  private technicienToTechnicienRequest(technicien: Technicien): any {
    let obj = {
      id: technicien.id,
      nom: technicien.nom,
      prenom: technicien.prenom,
      telephone: technicien.telephone,
      mail: technicien.mail,
      ordinateurId: technicien.ordinateurResponse?.id,
      compteId: technicien.compteResponse?.id,
    };
    return obj;
  }
}
