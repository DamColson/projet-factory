import { Salle } from './salle';
import { Stagiaire } from './stagiaire';

export class Ordinateur {
  constructor(
    public id?: number,
    public adresseMac?: string,
    public dateAchat?: Date,
    public libelle?: string,
    public os?: string,
    public status?: string,
    public salleResponse?: Salle
  ) {}
}
