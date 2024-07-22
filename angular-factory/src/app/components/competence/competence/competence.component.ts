import { Component } from '@angular/core';
import { Competence } from '../../../model/competence';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-competence',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './competence.component.html',
  styleUrl: './competence.component.css',
})
export class CompetenceComponent {
  competences: Competence[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private competenceSrv: CompetenceService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initCompetences();
    this.initMessage();
  }

  initCompetences() {
    this.competenceSrv.getAll().subscribe((competences) => {
      this.competences = competences;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Compétence ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Compétence ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.competenceSrv.delete(id).subscribe(() => {
      this.initCompetences();
      this.message = `Formateur ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
