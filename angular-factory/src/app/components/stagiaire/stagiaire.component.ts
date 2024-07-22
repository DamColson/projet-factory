import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { Stagiaire } from '../../model/stagiaire';
import { StagiaireService } from '../../services/stagiaire.service';

@Component({
  selector: 'app-stagiaire',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './stagiaire.component.html',
  styleUrl: './stagiaire.component.css',
})
export class StagiaireComponent {
  stagiaires: Stagiaire[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private stagiaireSrv: StagiaireService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initStagiaires();
    this.initMessage();
  }

  initStagiaires() {
    this.stagiaireSrv.getAll().subscribe((stagiaires) => {
      this.stagiaires = stagiaires;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Stagiaire ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Stagiaire ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.stagiaireSrv.delete(id).subscribe(() => {
      this.initStagiaires();
      this.message = `Stagiaire ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
