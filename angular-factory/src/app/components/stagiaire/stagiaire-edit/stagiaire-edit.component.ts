import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Ordinateur } from '../../../model/ordinateur';
import { Observable } from 'rxjs';
import { Stagiaire } from '../../../model/stagiaire';
import { StagiaireService } from '../../../services/stagiaire.service';
import { Compte } from '../../../model/compte';
import { Formation } from '../../../model/formation';
import { OrdinateurService } from '../../../services/ordinateur.service';
import { FormationService } from '../../../services/formation.service';
import { CompteService } from '../../../services/compte.service';

@Component({
  selector: 'app-stagiaire-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './stagiaire-edit.component.html',
  styleUrl: './stagiaire-edit.component.css',
})
export class StagiaireEditComponent {
  //filieres: Filiere[] = [];
  form!: FormGroup;
  OrdinateurObservable!: Observable<Ordinateur[]>;
  FormationObservable!: Observable<Formation[]>;
  CompteObservable!: Observable<Compte[]>;

  stagiaire: Stagiaire = new Stagiaire();

  constructor(
    private stagiaireSrv: StagiaireService,
    private ordinateurSrv: OrdinateurService,
    private formationSrv: FormationService,
    private compteSrv: CompteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.OrdinateurObservable = this.ordinateurSrv.getAllDisponible();
    this.FormationObservable = this.formationSrv.getAll();
    this.CompteObservable = this.compteSrv.getAllStag();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.stagiaireSrv.getById(params['id']).subscribe((stagiaire) => {
          this.stagiaire = stagiaire;
        });
      }
    });
  }
  save() {
    if (this.stagiaire.id) {
      this.stagiaireSrv.update(this.stagiaire).subscribe((stagiaire) => {
        this.router.navigateByUrl('/stagiaire?q=update&id=' + stagiaire.id);
      });
    } else {
      this.stagiaireSrv.create(this.stagiaire).subscribe((stagiaire) => {
        this.router.navigateByUrl('/stagiaire?q=create&id=' + stagiaire.id);
      });
    }
  }

  compareOn(o1: Ordinateur, o2: Ordinateur): boolean {
    return o1 && o2 ? o1.id === o2.id : false;
  }

  compareFn(f1: Formation, f2: Formation): boolean {
    return f1 && f2 ? f1.id === f2.id : false;
  }

  compareCn(c1: Compte, c2: Compte): boolean {
    return c1 && c2 ? c1.id === c2.id : false;
  }
}
