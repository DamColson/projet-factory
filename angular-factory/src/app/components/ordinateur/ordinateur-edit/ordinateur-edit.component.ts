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
import { Compte } from '../../../model/compte';
import { Formation } from '../../../model/formation';
import { Ordinateur } from '../../../model/ordinateur';
import { Stagiaire } from '../../../model/stagiaire';
import { CompteService } from '../../../services/compte.service';
import { FormationService } from '../../../services/formation.service';
import { OrdinateurService } from '../../../services/ordinateur.service';
import { StagiaireService } from '../../../services/stagiaire.service';
import { Salle } from '../../../model/salle';
import { SalleService } from '../../../services/salle.service';

@Component({
  selector: 'app-ordinateur-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './ordinateur-edit.component.html',
  styleUrl: './ordinateur-edit.component.css',
})
export class OrdinateurEditComponent {
  form!: FormGroup;
  SalleObservable!: Observable<Salle[]>;
  StagiaireObservable!: Observable<Stagiaire[]>;

  ordinateur: Ordinateur = new Ordinateur();

  constructor(
    private stagiaireSrv: StagiaireService,
    private ordinateurSrv: OrdinateurService,
    private salleSrv: SalleService,

    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.StagiaireObservable = this.stagiaireSrv.getAll();
    this.SalleObservable = this.salleSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.ordinateurSrv.getById(params['id']).subscribe((ordinateur) => {
          this.ordinateur = ordinateur;
        });
      }
    });
  }
  save() {
    if (this.ordinateur.id) {
      this.ordinateurSrv.update(this.ordinateur).subscribe((ordinateur) => {
        this.router.navigateByUrl('/ordinateur?q=update&id=' + ordinateur.id);
      });
    } else {
      this.ordinateurSrv.create(this.ordinateur).subscribe((ordinateur) => {
        this.router.navigateByUrl('/ordinateur?q=create&id=' + ordinateur.id);
      });
    }
  }

  compareStn(st1: Stagiaire, st2: Stagiaire): boolean {
    return st1 && st2 ? st1.id === st2.id : false;
  }

  compareSn(s1: Salle, s2: Salle): boolean {
    return s1 && s2 ? s1.id === s2.id : false;
  }
}
