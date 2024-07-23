import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Gestionnaire } from '../../../model/gestionnaire';
import { Formation } from '../../../model/formation';
import { FormationService } from '../../../services/formation.service';
import { GestionnaireService } from '../../../services/gestionnaire.service';

@Component({
  selector: 'app-formation-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './formation-edit.component.html',
  styleUrl: './formation-edit.component.css',
})
export class FormationEditComponent {
  //filieres: Filiere[] = [];
  form!: FormGroup;
  GestionnaireObservable!: Observable<Gestionnaire[]>;

  formation: Formation = new Formation();

  constructor(
    private formationSrv: FormationService,
    private gestionnaireSrv: GestionnaireService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.GestionnaireObservable = this.gestionnaireSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formationSrv.getById(params['id']).subscribe((formation) => {
          this.formation = formation;
        });
      }
    });
  }
  save() {
    if (this.formation.id) {
      this.formationSrv.update(this.formation).subscribe((formation) => {
        this.router.navigateByUrl('/formation?q=update&id=' + formation.id);
      });
    } else {
      this.formationSrv.create(this.formation).subscribe((formation) => {
        this.router.navigateByUrl('/formation?q=create&id=' + formation.id);
      });
    }
  }

  compareGn(g1: Gestionnaire, g2: Gestionnaire): boolean {
    return g1 && g2 ? g1.id === g2.id : false;
  }
}
