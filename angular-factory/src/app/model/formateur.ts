import { Bloc } from './bloc';
import { Competence } from './competence';
import { Compte } from './compte';
import { Ordinateur } from './ordinateur';
import { Videoprojecteur } from './videoprojecteur';

export class Formateur {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public telephone?: string,
    public mail?: string,
    public compteResponse?: Compte,
    public ordinateurResponse?: Ordinateur,
    public videoProjecteurResponse?: Videoprojecteur,
    public competencesResponse?: Competence[],
    public blocsResponse?: Bloc[]
  ) {}
}
