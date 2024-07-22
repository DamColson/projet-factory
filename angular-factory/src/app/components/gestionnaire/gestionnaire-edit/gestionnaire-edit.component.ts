import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Ordinateur } from '../../../model/ordinateur';
import { Compte } from '../../../model/compte';
import { Gestionnaire } from '../../../model/gestionnaire';
import { GestionnaireService } from '../../../services/gestionnaire.service';
import { OrdinateurService } from '../../../services/ordinateur.service';
import { CompteService } from '../../../services/compte.service';

@Component({
  selector: 'app-gestionnaire-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './gestionnaire-edit.component.html',
  styleUrl: './gestionnaire-edit.component.css',
})
export class GestionnaireEditComponent {
  //filieres: Filiere[] = [];
  form!: FormGroup;
  OrdinateurObservable!: Observable<Ordinateur[]>;
  CompteObservable!: Observable<Compte[]>;

  gestionnaire: Gestionnaire = new Gestionnaire();

  constructor(
    private gestionnaireSrv: GestionnaireService,
    private ordinateurSrv: OrdinateurService,
    private compteSrv: CompteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.OrdinateurObservable = this.ordinateurSrv.getAll();
    this.CompteObservable = this.compteSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.gestionnaireSrv.getById(params['id']).subscribe((gestionnaire) => {
          this.gestionnaire = gestionnaire;
        });
      }
    });
  }
  save() {
    if (this.gestionnaire.id) {
      this.gestionnaireSrv
        .update(this.gestionnaire)
        .subscribe((gestionnaire) => {
          this.router.navigateByUrl(
            '/gestionnaire?q=update&id=' + gestionnaire.id
          );
        });
    } else {
      this.gestionnaireSrv
        .create(this.gestionnaire)
        .subscribe((gestionnaire) => {
          this.router.navigateByUrl(
            '/formateur?q=create&id=' + gestionnaire.id
          );
        });
    }
  }

  compareOn(o1: Ordinateur, o2: Ordinateur): boolean {
    return o1 && o2 ? o1.id === o2.id : false;
  }

  compareCn(c1: Compte, c2: Compte): boolean {
    return c1 && c2 ? c1.id === c2.id : false;
  }
}
