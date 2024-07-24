import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Salle } from '../../../model/salle';
import { Formateur } from '../../../model/formateur';
import { Observable } from 'rxjs';
import { Videoprojecteur } from '../../../model/videoprojecteur';
import { VideoprojecteurService } from '../../../services/videoprojecteur.service';
import { FormateurService } from '../../../services/formateur.service';
import { SalleService } from '../../../services/salle.service';

@Component({
  selector: 'app-videoprojecteur-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './videoprojecteur-edit.component.html',
  styleUrl: './videoprojecteur-edit.component.css',
})
export class VideoprojecteurEditComponent {
  form!: FormGroup;

  SalleObservable!: Observable<Salle[]>;
  FormateurObservable!: Observable<Formateur[]>;

  videoprojecteur: Videoprojecteur = new Videoprojecteur();

  constructor(
    private videoprojecteurSrv: VideoprojecteurService,
    private salleSrv: SalleService,
    private formateurSrv: FormateurService,

    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.SalleObservable = this.salleSrv.getAll();
    this.FormateurObservable = this.formateurSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.videoprojecteurSrv
          .getById(params['id'])
          .subscribe((videoprojecteur) => {
            this.videoprojecteur = videoprojecteur;
          });
      }
    });
  }
  save() {
    if (this.videoprojecteur.id) {
      this.videoprojecteurSrv
        .update(this.videoprojecteur)
        .subscribe((videoprojecteur) => {
          this.router.navigateByUrl(
            '/videoprojecteur?q=update&id=' + videoprojecteur.id
          );
        });
    } else {
      this.videoprojecteurSrv
        .create(this.videoprojecteur)
        .subscribe((videoprojecteur) => {
          this.router.navigateByUrl(
            '/videoprojecteur?q=create&id=' + videoprojecteur.id
          );
        });
    }
  }

  compareS(s1: Salle, s2: Salle): boolean {
    return s1 && s2 ? s1.id === s2.id : false;
  }

  compareFr(f1: Formateur, f2: Formateur): boolean {
    return f1 && f2 ? f1.id === f2.id : false;
  }
}
