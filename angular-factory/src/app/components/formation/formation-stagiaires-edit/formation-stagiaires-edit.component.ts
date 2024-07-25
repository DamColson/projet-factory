import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Stagiaire } from '../../../model/stagiaire';
import { Observable } from 'rxjs';
import { Formation } from '../../../model/formation';
import { StagiaireService } from '../../../services/stagiaire.service';
import { FormationService } from '../../../services/formation.service';

@Component({
  selector: 'app-formation-stagiaires-edit',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './formation-stagiaires-edit.component.html',
  styleUrl: './formation-stagiaires-edit.component.css',
})
export class FormationStagiairesEditComponent {
  form!: FormGroup;
  stagiaire!: Stagiaire;
  stagiairesId: number[] = [];
  stagiairesObservable!: Observable<Stagiaire[]>;
  formation: Formation = new Formation();
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private stagiaireSrv: StagiaireService,
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.stagiairesObservable = this.stagiaireSrv.getAll();
    this.initFormation();
    this.initMessage();
  }

  initFormation() {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formationSrv.getById(params['id']).subscribe((formation) => {
          this.formation = formation;
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
    this.stagiaireSrv.getById(this.stagiaire.id!).subscribe((stagiaire) => {
      for (let stag of this.formation.stagiairesResponse!) {
        if (stag.id == this.stagiaire.id) {
          test = true;
        }
      }
      if (test == false) {
        this.formation.stagiairesResponse?.push(stagiaire);
        if (this.formation.id) {
          this.formationSrv.update(this.formation).subscribe((formation) => {
            this.router.navigateByUrl(
              '/formation/' + formation.id + '/stagiaires'
            );
          });
        }
      }
    });
  }

  delete(id: number) {
    this.stagiaireSrv.getById(id).subscribe((stagiaire) => {
      for (let comp of this.formation.stagiairesResponse!) {
        if (comp.id == stagiaire.id) {
          this.formation.stagiairesResponse?.splice(
            this.formation.stagiairesResponse.indexOf(comp),
            1
          );
        }
      }
      this.formationSrv.update(this.formation).subscribe((formation) => {
        this.router.navigateByUrl('/formation/' + formation.id + '/stagiaires');
      });
    });
  }
}
