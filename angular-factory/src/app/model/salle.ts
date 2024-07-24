import { Ordinateur } from './ordinateur';
import { Videoprojecteur } from './videoprojecteur';

export class Salle {
  constructor(
    public id?: number,
    public superficie?: number,
    public libelle?: string
  ) {}
}
