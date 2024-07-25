import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { Compte } from '../../../model/compte';
import { CompteService } from '../../../services/compte.service';

@Component({
  selector: 'app-compte',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './compte.component.html',
  styleUrl: './compte.component.css',
})
export class CompteComponent {
  comptes: Compte[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private compteSrv: CompteService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initTechnicien();
    this.initMessage();
  }

  initTechnicien() {
    this.compteSrv.getAll().subscribe((comptes) => {
      this.comptes = comptes;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Compte crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Compte ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.compteSrv.delete(id).subscribe(() => {
      this.initTechnicien();
      this.message = `Compte ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
