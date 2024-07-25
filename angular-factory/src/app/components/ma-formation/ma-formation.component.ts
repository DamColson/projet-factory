import { Component } from '@angular/core';
import { Bloc } from '../../model/bloc';
import { BlocService } from '../../services/bloc.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormationService } from '../../services/formation.service';
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
        for (let index = 0; index < formation.blocsResponse?.length!; index++) {
          this.blocs.push(formation.blocsResponse![index]);
        }
        this.blocs != formation.blocsResponse;
      });
    });
  }
}
