import { Component } from '@angular/core';
import { Competence } from '../../../model/competence';
import { RouterLink } from '@angular/router';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-competence',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './competence.component.html',
  styleUrl: './competence.component.css',
})
export class CompetenceComponent {
  competences: Competence[] = [];

  constructor(private competenceSrv: CompetenceService) {
    this.initCompetence();
  }

  delete(id: number) {
    this.competenceSrv.delete(id).subscribe(() => {
      this.initCompetence();
    });
  }

  initCompetence() {
    this.competenceSrv.getAll().subscribe((competences) => {
      this.competences = competences;
    });
  }
}
