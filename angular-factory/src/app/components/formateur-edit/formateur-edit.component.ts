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
import { Observable } from 'rxjs';
import { Ordinateur } from '../../model/ordinateur';
import { Formateur } from '../../model/formateur';
import { FormateurService } from '../../services/formateur.service';
import { OrdinateurService } from '../../services/ordinateur.service';
import { Videoprojecteur } from '../../model/videoprojecteur';
import { Compte } from '../../model/compte';
import { VideoprojecteurService } from '../../services/videoprojecteur.service';
import { CompteService } from '../../services/compte.service';

@Component({
  selector: 'app-formateur-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './formateur-edit.component.html',
  styleUrl: './formateur-edit.component.css',
})
export class FormateurEditComponent {
  //filieres: Filiere[] = [];
  form!: FormGroup;
  OrdinateurObservable!: Observable<Ordinateur[]>;
  VideoprojecteurObservable!: Observable<Videoprojecteur[]>;
  CompteObservable!: Observable<Compte[]>;

  formateur: Formateur = new Formateur();

  constructor(
    private formateurSrv: FormateurService,
    private ordinateurSrv: OrdinateurService,
    private videoprojecteurSrv: VideoprojecteurService,
    private compteSrv: CompteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.OrdinateurObservable = this.ordinateurSrv.getAll();
    this.VideoprojecteurObservable = this.videoprojecteurSrv.getAll();
    this.CompteObservable = this.compteSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.formateurSrv.getById(params['id']).subscribe((formateur) => {
          this.formateur = formateur;
        });
      }
    });
  }
  save() {
    if (this.formateur.id) {
      this.formateurSrv.update(this.formateur).subscribe((formateur) => {
        this.router.navigateByUrl('/formateur?q=update&id=' + formateur.id);
      });
    } else {
      this.formateurSrv.create(this.formateur).subscribe((formateur) => {
        this.router.navigateByUrl('/formateur?q=create&id=' + formateur.id);
      });
    }
  }

  compareOn(o1: Ordinateur, o2: Ordinateur): boolean {
    return o1 && o2 ? o1.id === o2.id : false;
  }

  compareVn(v1: Videoprojecteur, v2: Videoprojecteur): boolean {
    return v1 && v2 ? v1.id === v2.id : false;
  }

  compareCn(c1: Compte, c2: Compte): boolean {
    return c1 && c2 ? c1.id === c2.id : false;
  }
}
