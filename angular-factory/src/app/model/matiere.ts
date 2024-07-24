import { Competence } from './competence';

export class Matiere {
  constructor(
    public id?: number,
    public titre?: string,
    public contenu?: string,
    public competencesResponse?: Competence[]
  ) {}
}
