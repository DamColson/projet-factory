import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { Bloc } from '../../../model/bloc';
import { BlocService } from '../../../services/bloc.service';

@Component({
  selector: 'app-bloc-affichage',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, DatePipe],
  templateUrl: './bloc-affichage.component.html',
  styleUrl: './bloc-affichage.component.css',
})
export class BlocAffichageComponent {
  bloc: Bloc = new Bloc();
  location: Location | undefined;

  constructor(
    private blocSrv: BlocService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.blocSrv.getById(params['id']).subscribe((bloc) => {
          this.bloc = bloc;
        });
      }
    });
  }

  goBack(): void {
    window.history.back();
  }
}
