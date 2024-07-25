import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { BlocService } from '../../services/bloc.service';
import { FormateurService } from '../../services/formateur.service';
import { Bloc } from '../../model/bloc';

@Component({
  selector: 'app-mes-blocs',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './mes-blocs.component.html',
  styleUrl: './mes-blocs.component.css',
})
export class MesBlocsComponent {
  blocs: Bloc[] = [];
  formateur: any = '';

  constructor(
    private blocSrv: BlocService,
    private formateurSrv: FormateurService
  ) {}

  ngOnInit(): void {
    this.initBlocs();
  }

  initBlocs() {
    let formateurId = JSON.parse(localStorage.getItem('compte')!)
      .formateurResponse?.id;
    this.formateurSrv.getById(formateurId).subscribe((formateur) => {
      this.formateur = formateur;
      this.blocs != formateur.blocsResponse;
    });
  }
}
