import { Formateur } from './formateur';
import { Salle } from './salle';

export class Videoprojecteur {
  constructor(
    public id?: number,
    public libelle?: string,
    public adresseMac?: string,
    public dateAchat?: Date,
    public salleResponse?: Salle,
    public status?: string
  ) {}
}
