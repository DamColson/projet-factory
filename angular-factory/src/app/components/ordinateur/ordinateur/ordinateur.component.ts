import { Component } from '@angular/core';
import { OrdinateurService } from '../../../services/ordinateur.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Ordinateur } from '../../../model/ordinateur';

@Component({
  selector: 'app-ordinateur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './ordinateur.component.html',
  styleUrl: './ordinateur.component.css',
})
export class OrdinateurComponent {
  ordinateurs: Ordinateur[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private ordinateurSrv: OrdinateurService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initOrdinateurs();
    this.initMessage();
  }

  initOrdinateurs() {
    this.ordinateurSrv.getAll().subscribe((ordinateurs) => {
      this.ordinateurs = ordinateurs;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Ordinateur ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Ordinateur ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.ordinateurSrv.delete(id).subscribe(() => {
      this.initOrdinateurs();
      this.message = ` Ordinateur ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
