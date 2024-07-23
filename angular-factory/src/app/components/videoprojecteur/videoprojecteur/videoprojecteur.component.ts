import { Component } from '@angular/core';
import { Videoprojecteur } from '../../../model/videoprojecteur';
import { VideoprojecteurService } from '../../../services/videoprojecteur.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-videoprojecteur',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './videoprojecteur.component.html',
  styleUrl: './videoprojecteur.component.css',
})
export class VideoprojecteurComponent {
  videoprojecteurs: Videoprojecteur[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private videoprojecteurSrv: VideoprojecteurService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initVideoProjecteurs();
    this.initMessage();
  }

  initVideoProjecteurs() {
    this.videoprojecteurSrv.getAll().subscribe((videoprojecteurs) => {
      this.videoprojecteurs = videoprojecteurs;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Vidéoprojecteur ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Vidéoprojecteur ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.videoprojecteurSrv.delete(id).subscribe(() => {
      this.initVideoProjecteurs();
      this.message = ` Vidéoprojecteur ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
