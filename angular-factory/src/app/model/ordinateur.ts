import { Salle } from './salle';

export class Ordinateur {
  constructor(
    public id?: number,
    public adresseMac?: number,
    public dateAchat?: Date,
    public libelle?: string,
    public os?: string,
    public status?: string,
    public salleResponse?: Salle
  ) {}
}
