import { Component } from '@angular/core';
import { Competence } from '../../../model/competence';
import { CompetenceService } from '../../../services/competence.service';
import { Formateur } from '../../../model/formateur';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { FormateurService } from '../../../services/formateur.service';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AsyncPipe } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formateur-competences-edit',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './formateur-competences-edit.component.html',
  styleUrl: './formateur-competences-edit.component.css',
})
export class FormateurCompetencesEditComponent {
  form!: FormGroup;
  competences: Competence[] = [];
  CompetencesObservable!: Observable<Competence[]>;
  formateur: Formateur = new Formateur();
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private competenceSrv: CompetenceService,
    private formateurSrv: FormateurService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.CompetencesObservable = this.competenceSrv.getAll();
    this.initCompetences();
    this.initMessage();
  }

  initCompetences() {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formateurSrv.getById(params['id']).subscribe((formateur) => {
          this.formateur = formateur;
        });
      }
    });
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

  save() {
    console.log(this.formateur.competencesResponse);
    this.formateurSrv.update(this.formateur).subscribe((ordinateur) => {});
  }

  delete(id: number) {
    this.competenceSrv.delete(id).subscribe(() => {
      this.initCompetences();
      this.message = `Compétence ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
