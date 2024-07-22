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
import { Salle } from '../../../model/salle';
import { Matiere } from '../../../model/matiere';
import { Formateur } from '../../../model/formateur';
import { Bloc } from '../../../model/bloc';
import { BlocService } from '../../../services/bloc.service';
import { SalleService } from '../../../services/salle.service';
import { MatiereService } from '../../../services/matiere.service';
import { FormateurService } from '../../../services/formateur.service';

@Component({
  selector: 'app-bloc-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './bloc-edit.component.html',
  styleUrl: './bloc-edit.component.css',
})
export class BlocEditComponent {
  //filieres: Filiere[] = [];
  form!: FormGroup;
  SalleObservable!: Observable<Salle[]>;
  MatiereObservable!: Observable<Matiere[]>;
  FormateurObservable!: Observable<Formateur[]>;

  bloc: Bloc = new Bloc();

  constructor(
    private blocSrv: BlocService,
    private salleSrv: SalleService,
    private matiereSrv: MatiereService,
    private formateurSrv: FormateurService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.SalleObservable = this.salleSrv.getAll();
    this.MatiereObservable = this.matiereSrv.getAll();
    this.FormateurObservable = this.formateurSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.blocSrv.getById(params['id']).subscribe((bloc) => {
          this.bloc = bloc;
        });
      }
    });
  }
  save() {
    if (this.bloc.id) {
      this.blocSrv.update(this.bloc).subscribe((bloc) => {
        this.router.navigateByUrl('/bloc?q=update&id=' + bloc.id);
      });
    } else {
      this.blocSrv.create(this.bloc).subscribe((bloc) => {
        this.router.navigateByUrl('/bloc?q=create&id=' + bloc.id);
      });
    }
  }

  compareSn(s1: Salle, s2: Salle): boolean {
    return s1 && s2 ? s1.id === s2.id : false;
  }

  compareMn(m1: Matiere, m2: Matiere): boolean {
    return m1 && m2 ? m1.id === m2.id : false;
  }

  compareFn(f1: Formateur, f2: Formateur): boolean {
    return f1 && f2 ? f1.id === f2.id : false;
  }
}
