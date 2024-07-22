import { Component } from '@angular/core';
import { Formation } from '../../../model/formation';
import { FormationService } from '../../../services/formation.service';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-formation',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './formation.component.html',
  styleUrl: './formation.component.css',
})
export class FormationComponent {
  formations: Formation[] = [];

  constructor(private formationSrv: FormationService) {
    this.initFormation();
  }

  delete(id: number) {
    this.formationSrv.delete(id).subscribe(() => {
      this.initFormation();
    });
  }

  initOrdinateur() {
    this.formationSrv.getAll().subscribe((formations) => {
      this.formations = formations;
    });
  }
}
