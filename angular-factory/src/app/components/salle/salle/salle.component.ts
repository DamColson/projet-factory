import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Salle } from '../../../model/salle';
import { SalleService } from '../../../services/salle.service';

@Component({
  selector: 'app-salle',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './salle.component.html',
  styleUrl: './salle.component.css',
})
export class SalleComponent {
  salles: Salle[] = [];

  constructor(private salleSrv: SalleService) {
    this.initSalle();
  }

  delete(id: number) {
    this.salleSrv.delete(id).subscribe(() => {
      this.initSalle();
    });
  }

  initSalle() {
    this.salleSrv.getAll().subscribe((salles) => {
      this.salles = salles;
    });
  }
}
