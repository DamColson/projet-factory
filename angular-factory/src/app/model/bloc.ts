import { Formateur } from './formateur';
import { Matiere } from './matiere';
import { Salle } from './salle';

export class Bloc {
  constructor(
    public id?: number,
    public dateDebut?: Date,
    public dateFin?: Date,
    public formateurResponse?: Formateur,
    public matiereResponse?: Matiere,
    public salleResponse?: Salle,
    public code?: string,
    public objectif?: string
  ) {}
}
