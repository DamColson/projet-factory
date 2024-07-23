import { Formateur } from './formateur';
import { Salle } from './salle';

export class Videoprojecteur {
  constructor(
    public id?: number,
    public adresseMac?: number,
    public dateAchat?: Date,
    public libelle?: string,
    public status?: string,
    public salleResponse?: Salle,
    public formateurResponse?: Formateur
  ) {}
}
