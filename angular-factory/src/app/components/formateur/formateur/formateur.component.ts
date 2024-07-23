import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { Formateur } from '../../../model/formateur';
import { FormateurService } from '../../../services/formateur.service';

@Component({
  selector: 'app-formateur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './formateur.component.html',
  styleUrl: './formateur.component.css',
})
export class FormateurComponent {
  formateurs: Formateur[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private formateurSrv: FormateurService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initFormateurs();
    this.initMessage();
  }

  initFormateurs() {
    this.formateurSrv.getAll().subscribe((formateurs) => {
      this.formateurs = formateurs;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Formateur ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Formateur ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.formateurSrv.delete(id).subscribe(() => {
      this.initFormateurs();
      this.message = `Formateur ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
