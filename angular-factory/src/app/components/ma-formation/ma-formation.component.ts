import { Component } from '@angular/core';
import { Bloc } from '../../model/bloc';
import { BlocService } from '../../services/bloc.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Formation } from '../../model/formation';
import { FormationService } from '../../services/formation.service';
import { Compte } from '../../model/compte';
import { StagiaireService } from '../../services/stagiaire.service';

@Component({
  selector: 'app-ma-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './ma-formation.component.html',
  styleUrl: './ma-formation.component.css',
})
export class MaFormationComponent {
  blocs: Bloc[] = [];
  formation: any = '';

  constructor(
    private blocSrv: BlocService,
    private stagiaireSrv: StagiaireService,
    private formationSrv: FormationService
  ) {}

  ngOnInit(): void {
    this.initBlocs();
  }

  initBlocs() {
    let stagiaireId = JSON.parse(localStorage.getItem('compte')!)
      .stagiaireResponse?.id;

    this.stagiaireSrv.getById(stagiaireId).subscribe((stagiaire) => {
      this.formation = stagiaire.formationResponse;
      this.formationSrv.getById(this.formation.id).subscribe((formation) => {
        this.blocs != formation.blocsResponse;
      });
    });
  }
}
