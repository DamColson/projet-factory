import { Competence } from './competence';

export class Matiere {
  constructor(
    public id?: number,
    public contenu?: string,
    public titre?: string,
    public competencesResponse?: Competence[]
  ) {}
}
