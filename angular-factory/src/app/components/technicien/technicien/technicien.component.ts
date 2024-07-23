import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { Technicien } from '../../../model/technicien';
import { TechnicienService } from '../../../services/technicien.service';

@Component({
  selector: 'app-technicien',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './technicien.component.html',
  styleUrl: './technicien.component.css',
})
export class TechnicienComponent {
  techniciens: Technicien[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private technicienSrv: TechnicienService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initTechniciens();
    this.initMessage();
  }

  initTechniciens() {
    this.technicienSrv.getAll().subscribe((techniciens) => {
      this.techniciens = techniciens;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Technicien ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Technicien ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.technicienSrv.delete(id).subscribe(() => {
      this.initTechniciens();
      this.message = `Technicien ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
