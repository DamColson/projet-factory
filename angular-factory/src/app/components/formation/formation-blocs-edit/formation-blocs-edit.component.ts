import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Bloc } from '../../../model/bloc';
import { Formation } from '../../../model/formation';
import { BlocService } from '../../../services/bloc.service';
import { FormationService } from '../../../services/formation.service';
import { AsyncPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-formation-blocs-edit',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
    FormsModule,
    DatePipe,
  ],
  templateUrl: './formation-blocs-edit.component.html',
  styleUrl: './formation-blocs-edit.component.css',
})
export class FormationBlocsEditComponent {
  form!: FormGroup;
  bloc!: Bloc;
  blocsId: number[] = [];
  blocsObservable!: Observable<Bloc[]>;
  formation: Formation = new Formation();
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private blocSrv: BlocService,
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.blocsObservable = this.blocSrv.getAll();
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
    this.blocSrv.getById(this.bloc.id!).subscribe((bloc) => {
      for (let comp of this.formation.blocsResponse!) {
        if (comp.id == this.bloc.id) {
          test = true;
        }
      }
      if (test == false) {
        this.formation.blocsResponse?.push(bloc);
        if (this.formation.id) {
          this.formationSrv.update(this.formation).subscribe((formation) => {
            this.router.navigateByUrl('/formation/' + formation.id + '/blocs');
          });
        }
      }
    });
  }

  delete(id: number) {
    this.blocSrv.getById(id).subscribe((bloc) => {
      for (let comp of this.formation.blocsResponse!) {
        if (comp.id == bloc.id) {
          this.formation.blocsResponse?.splice(
            this.formation.blocsResponse.indexOf(comp),
            1
          );
        }
      }
      this.formationSrv.update(this.formation).subscribe((formation) => {
        this.router.navigateByUrl('/formation/' + formation.id + '/blocs');
      });
    });
  }
}
