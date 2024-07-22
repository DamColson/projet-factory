export class Formation {
  constructor(
    public id?: number,
    public debut_formation?: Date,
    public fin_formation?: Date,
    public gestionnaire_id?: number,
    public prerequis?: string
  ) {}
}
