import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';

import { Matiere } from '../../../model/matiere';
import { MatiereService } from '../../../services/matiere.service';

@Component({
  selector: 'app-matiere-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './matiere-edit.component.html',
  styleUrl: './matiere-edit.component.css',
})
export class MatiereEditComponent {
  form!: FormGroup;

  matiere: Matiere = new Matiere();

  constructor(
    private matiereSrv: MatiereService,

    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.matiereSrv.getById(params['id']).subscribe((matiere) => {
          this.matiere = matiere;
        });
      }
    });
  }
  save() {
    if (this.matiere.id) {
      this.matiereSrv.update(this.matiere).subscribe((matiere) => {
        this.router.navigateByUrl('/matiere?q=update&id=' + matiere.id);
      });
    } else {
      this.matiereSrv.create(this.matiere).subscribe((matiere) => {
        this.router.navigateByUrl('/matiere?q=create&id=' + matiere.id);
      });
    }
  }
}
