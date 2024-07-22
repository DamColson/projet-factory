export class Bloc {
  constructor(
    public id?: number,
    public matiere_id?: number,
    public salle_id?: number,
    public date_debut?: Date,
    public date_fin?: Date,
    public formateur?: string,
    public code?: string,
    public objectif?: string
  ) {}
}
