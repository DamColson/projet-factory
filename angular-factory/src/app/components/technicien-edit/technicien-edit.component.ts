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
import { Technicien } from '../../model/technicien';
import { TechnicienService } from '../../services/technicien.service';
import { OrdinateurService } from '../../services/ordinateur.service';

@Component({
  selector: 'app-technicien-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './technicien-edit.component.html',
  styleUrl: './technicien-edit.component.css',
})
export class TechnicienEditComponent {
  //filieres: Filiere[] = [];
  form!: FormGroup;
  OrdinateurObservable!: Observable<Ordinateur[]>;

  technicien: Technicien = new Technicien();

  constructor(
    private technicienSrv: TechnicienService,
    private ordinateurSrv: OrdinateurService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = new FormGroup({
      nom: new FormControl('', Validators.required),
      prenom: new FormControl('', Validators.required),
      mail: new FormControl('', Validators.required),
      telephone: new FormControl('', Validators.required),
      ordinateurResponse: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    this.OrdinateurObservable = this.ordinateurSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.technicienSrv.getById(params['id']).subscribe((technicien) => {
          this.technicien = technicien;
        });
      }
    });
  }
  save() {
    if (this.technicien.id) {
      this.technicienSrv.update(this.technicien).subscribe((technicien) => {
        this.router.navigateByUrl('/technicien?q=update&id=' + technicien.id);
      });
    } else {
      this.technicienSrv.create(this.technicien).subscribe((technicien) => {
        this.router.navigateByUrl('/technicien?q=create&id=' + technicien.id);
      });
    }
  }

  compareFn(o1: Ordinateur, o2: Ordinateur): boolean {
    return o1 && o2 ? o1.id === o2.id : false;
  }
}
