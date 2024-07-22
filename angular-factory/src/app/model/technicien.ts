import { Compte } from './compte';
import { Ordinateur } from './ordinateur';

export class Technicien {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public telephone?: string,
    public mail?: string,
    public compteResponse?: Compte,
    public ordinateurResponse?: Ordinateur
  ) {}
}
