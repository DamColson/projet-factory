import { Bloc } from './bloc';
import { Gestionnaire } from './gestionnaire';
import { Stagiaire } from './stagiaire';

export class Formation {
  constructor(
    public id?: number,
    public debut?: Date,
    public fin?: Date,
    public gestionnaireResponse?: Gestionnaire,
    public prerequis?: string,
    public stagiairesResponse?: Stagiaire[],
    public blocsResponse?: Bloc[]
  ) {}
}
