import { Compte } from './compte';
import { Formation } from './formation';
import { Ordinateur } from './ordinateur';

export class Stagiaire {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public telephone?: string,
    public mail?: string,
    public compteResponse?: Compte,
    public ordinateurResponse?: Ordinateur,
    public formationResponse?: Formation
  ) {}
}
