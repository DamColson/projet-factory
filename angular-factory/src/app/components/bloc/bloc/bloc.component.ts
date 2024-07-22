import { Component } from '@angular/core';
import { Bloc } from '../../../model/bloc';
import { BlocService } from '../../../services/bloc.service';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-bloc',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './bloc.component.html',
  styleUrl: './bloc.component.css',
})
export class BlocComponent {
  blocs: Bloc[] = [];

  constructor(private blocSrv: BlocService) {
    this.initBloc();
  }

  delete(id: number) {
    this.blocSrv.delete(id).subscribe(() => {
      this.initBloc();
    });
  }

  initBloc() {
    this.blocSrv.getAll().subscribe((blocs) => {
      this.blocs = blocs;
    });
  }
}
