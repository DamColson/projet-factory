export class Ordinateur {
  constructor(
    public id?: number,
    public adresse_mac?: number,
    public date_achat?: Date,
    public libelle?: string,
    public os?: string,
    public status?: string
  ) {}
}
