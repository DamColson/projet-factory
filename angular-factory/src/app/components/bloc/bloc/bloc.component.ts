import { Component } from '@angular/core';
import { Bloc } from '../../../model/bloc';
import { BlocService } from '../../../services/bloc.service';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-bloc',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './bloc.component.html',
  styleUrl: './bloc.component.css',
})
export class BlocComponent {
  blocs: Bloc[] = [];
  message = '';
  showMessage = false;
  style = 'info';

  constructor(
    private blocSrv: BlocService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initBlocs();
    this.initMessage();
  }

  initBlocs() {
    this.blocSrv.getAll().subscribe((blocs) => {
      this.blocs = blocs;
    });
  }

  initMessage() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['q']) {
        if (params['q'] == 'create') {
          this.message = `Bloc ${params['id']} crée `;
          this.style = 'alert-success';
        } else if (params['q'] == 'update') {
          this.message = `Bloc ${params['id']} mis à jour `;
          this.style = 'alert-info';
        }
      }
    });
    this.showMessage = true;
  }

  delete(id: number) {
    this.blocSrv.delete(id).subscribe(() => {
      this.initBlocs();
      this.message = `Bloc ${id} supprimé `;
      this.style = 'alert-danger';
    });
  }
}
