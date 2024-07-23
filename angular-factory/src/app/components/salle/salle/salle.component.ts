import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { Salle } from '../../../model/salle';
import { SalleService } from '../../../services/salle.service';

@Component({
  selector: 'app-salle',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './salle.component.html',
  styleUrl: './salle.component.css',
})
export class SalleComponent {
  salles: Salle[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private salleSrv: SalleService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initSalles();
    this.initMessage();
  }

  initSalles() {
    this.salleSrv.getAll().subscribe((salles) => {
      this.salles = salles;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Salle ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Salle ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.salleSrv.delete(id).subscribe(() => {
      this.initSalles();
      this.message = ` Salle ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
