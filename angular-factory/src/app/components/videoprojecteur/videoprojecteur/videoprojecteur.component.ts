import { Component } from '@angular/core';
import { Videoprojecteur } from '../../../model/videoprojecteur';
import { VideoprojecteurService } from '../../../services/videoprojecteur.service';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-videoprojecteur',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './videoprojecteur.component.html',
  styleUrl: './videoprojecteur.component.css',
})
export class VideoprojecteurComponent {
  videoprojecteurs: Videoprojecteur[] = [];

  constructor(private videoprojecteurrSrv: VideoprojecteurService) {
    this.initVideoprojecteur();
  }

  delete(id: number) {
    this.videoprojecteurrSrv.delete(id).subscribe(() => {
      this.initVideoprojecteur();
    });
  }

  initVideoprojecteur() {
    this.videoprojecteurrSrv.getAll().subscribe((videoprojecteurs) => {
      this.videoprojecteurs = videoprojecteurs;
    });
  }
}
