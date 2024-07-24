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
  competence!: Competence;
  competencesId: number[] = [];
  competencesObservable!: Observable<Competence[]>;
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
    this.competencesObservable = this.competenceSrv.getAll();
    this.initFormateur();
    this.initMessage();
  }

  initFormateur() {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formateurSrv.getById(params['id']).subscribe((formateur) => {
          this.formateur = formateur;
        });
      }
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
    this.competenceSrv.getById(this.competence.id!).subscribe((competence) => {
      this.formateur.competencesResponse?.push(competence);
      if (this.formateur.id) {
        this.formateurSrv.update(this.formateur).subscribe((formateur) => {
          this.router.navigateByUrl(
            '/formateur/' + formateur.id + '/competences'
          );
        });
      }
    });
  }

  delete(id: number) {
    this.competenceSrv.getById(id).subscribe((competence) => {
      console.log(this.formateur);
      for (let comp of this.formateur.competencesResponse!) {
        if (comp.id == competence.id) {
          this.formateur.competencesResponse?.splice(
            this.formateur.competencesResponse.indexOf(comp),
            1
          );
        }
      }
      this.formateurSrv.update(this.formateur).subscribe((formateur) => {
        this.router.navigateByUrl(
          '/formateur/' + formateur.id + '/competences'
        );
      });
    });
  }
}
