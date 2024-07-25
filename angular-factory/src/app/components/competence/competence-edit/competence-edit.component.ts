import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
import { Competence } from '../../../model/competence';
import { CompetenceService } from '../../../services/competence.service';

@Component({
  selector: 'app-competence-edit',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    ReactiveFormsModule,
  ],
  templateUrl: './competence-edit.component.html',
  styleUrl: './competence-edit.component.css',
})
export class CompetenceEditComponent {
  form!: FormGroup;

  competence: Competence = new Competence();

  constructor(
    private competenceSrv: CompetenceService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.competenceSrv.getById(params['id']).subscribe((competence) => {
          this.competence = competence;
        });
      }
    });
  }
  save() {
    if (this.competence.id) {
      this.competenceSrv.update(this.competence).subscribe((competence) => {
        this.router.navigateByUrl('/competence?q=update&id=' + competence.id);
      });
    } else {
      this.competenceSrv.create(this.competence).subscribe((competence) => {
        this.router.navigateByUrl('/competence?q=create&id=' + competence.id);
      });
    }
  }
}
