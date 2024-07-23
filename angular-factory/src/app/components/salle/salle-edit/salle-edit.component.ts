import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Videoprojecteur } from '../../../model/videoprojecteur';
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
import { VideoprojecteurService } from '../../../services/videoprojecteur.service';

@Component({
  selector: 'app-salle-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './salle-edit.component.html',
  styleUrl: './salle-edit.component.css',
})
export class SalleEditComponent {
  form!: FormGroup;

  VideoprojecteurObservable!: Observable<Videoprojecteur[]>;

  salle: Salle = new Salle();

  constructor(
    private salleSrv: SalleService,
    private videoprojecteurSrv: VideoprojecteurService,

    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.VideoprojecteurObservable = this.videoprojecteurSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.salleSrv.getById(params['id']).subscribe((salle) => {
          this.salle = salle;
        });
      }
    });
  }
  save() {
    if (this.salle.id) {
      this.salleSrv.update(this.salle).subscribe((salle) => {
        this.router.navigateByUrl('/salle?q=update&id=' + salle.id);
      });
    } else {
      this.salleSrv.create(this.salle).subscribe((salle) => {
        this.router.navigateByUrl('/salle?q=create&id=' + salle.id);
      });
    }
  }

  compareOn(v1: Videoprojecteur, v2: Videoprojecteur): boolean {
    return v1 && v2 ? v1.id === v2.id : false;
  }
}
