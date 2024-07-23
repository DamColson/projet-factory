import { Component } from '@angular/core';
import { Formation } from '../../../model/formation';
import { FormationService } from '../../../services/formation.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-formation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './formation.component.html',
  styleUrl: './formation.component.css',
})
export class FormationComponent {
  formations: Formation[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private formationSrv: FormationService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initFormations();
    this.initMessage();
  }

  initFormations() {
    this.formationSrv.getAll().subscribe((formations) => {
      this.formations = formations;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Formation ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Formation ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.formationSrv.delete(id).subscribe(() => {
      this.initFormations();
      this.message = `Formation ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
