import { Component } from '@angular/core';
import { Gestionnaire } from '../../../model/gestionnaire';
import { GestionnaireService } from '../../../services/gestionnaire.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-gestionnaire',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './gestionnaire.component.html',
  styleUrl: './gestionnaire.component.css',
})
export class GestionnaireComponent {
  gestionnaires: Gestionnaire[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private gestionnaireSrv: GestionnaireService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initGestionnaires();
    this.initMessage();
  }

  initGestionnaires() {
    this.gestionnaireSrv.getAll().subscribe((gestionnaires) => {
      this.gestionnaires = gestionnaires;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Gestionnaire ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Gestionnaire ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.gestionnaireSrv.delete(id).subscribe(() => {
      this.initGestionnaires();
      this.message = ` Gestionnaire ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
