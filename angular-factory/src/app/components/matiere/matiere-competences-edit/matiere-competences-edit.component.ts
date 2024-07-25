import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Competence } from '../../../model/competence';
import { Observable } from 'rxjs';
import { CompetenceService } from '../../../services/competence.service';
import { Matiere } from '../../../model/matiere';
import { MatiereService } from '../../../services/matiere.service';

@Component({
  selector: 'app-matiere-competences-edit',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './matiere-competences-edit.component.html',
  styleUrl: './matiere-competences-edit.component.css',
})
export class MatiereCompetencesEditComponent {
  form!: FormGroup;
  competence!: Competence;
  competencesId: number[] = [];
  competencesObservable!: Observable<Competence[]>;
  matiere: Matiere = new Matiere();
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private competenceSrv: CompetenceService,
    private matiereSrv: MatiereService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.competencesObservable = this.competenceSrv.getAll();
    this.initMatiere();
    this.initMessage();
  }

  initMatiere() {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.matiereSrv.getById(params['id']).subscribe((matiere) => {
          this.matiere = matiere;
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
    let test: Boolean = false;
    this.competenceSrv.getById(this.competence.id!).subscribe((competence) => {
      for (let comp of this.matiere.competencesResponse!) {
        if (comp.id == this.competence.id) {
          test = true;
        }
      }
      if (test == false) {
        this.matiere.competencesResponse?.push(competence);
        if (this.matiere.id) {
          this.matiereSrv.update(this.matiere).subscribe((matiere) => {
            this.router.navigateByUrl(
              '/matiere/' + matiere.id + '/competences'
            );
          });
        }
      }
    });
  }

  delete(id: number) {
    this.competenceSrv.getById(id).subscribe((competence) => {
      for (let comp of this.matiere.competencesResponse!) {
        if (comp.id == competence.id) {
          this.matiere.competencesResponse?.splice(
            this.matiere.competencesResponse.indexOf(comp),
            1
          );
        }
      }
      this.matiereSrv.update(this.matiere).subscribe((matiere) => {
        this.router.navigateByUrl('/matiere/' + matiere.id + '/competences');
      });
    });
  }
}
