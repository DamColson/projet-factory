import { Component } from '@angular/core';
import { Matiere } from '../../../model/matiere';
import { MatiereService } from '../../../services/matiere.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-matiere',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './matiere.component.html',
  styleUrl: './matiere.component.css',
})
export class MatiereComponent {
  matieres: Matiere[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private matiereSrv: MatiereService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initMatieres();
    this.initMessage();
  }

  initMatieres() {
    this.matiereSrv.getAll().subscribe((matieres) => {
      this.matieres = matieres;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Matière ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Matière ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.matiereSrv.delete(id).subscribe(() => {
      this.initMatieres();
      this.message = ` Matiere ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
