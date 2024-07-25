import { Formateur } from './formateur';
import { Stagiaire } from './stagiaire';

export class Compte {
  constructor(
    public id?: number,
    public login?: string,
    public role?: string,
    public stagiaireResponse?: Stagiaire,
    public formateurResponse?: Formateur
  ) {}
}
