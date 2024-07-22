import { Component } from '@angular/core';
import { Matiere } from '../../../model/matiere';
import { MatiereService } from '../../../services/matiere.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-matiere',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './matiere.component.html',
  styleUrl: './matiere.component.css',
})
export class MatiereComponent {
  matieres: Matiere[] = [];

  constructor(private matiereSrv: MatiereService) {
    this.initMatiere();
  }

  delete(id: number) {
    this.matiereSrv.delete(id).subscribe(() => {
      this.initMatiere();
    });
  }

  initMatiere() {
    this.matiereSrv.getAll().subscribe((matieres) => {
      this.matieres = matieres;
    });
  }
}
