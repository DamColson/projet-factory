import { Component } from '@angular/core';
import { OrdinateurService } from '../../../services/ordinateur.service';
import { Ordinateur } from '../../../model/ordinateur';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-ordinateur',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './ordinateur.component.html',
  styleUrl: './ordinateur.component.css'
})
export class OrdinateurComponent {
ordinateurs:Ordinateur[] = [];
}

constructor(private ordinateurSrv:OrdinateurService){
  this.initOrdinateur();
}

delete(id:number) {
  this.ordinateurSrv.delete(id).subscribe(() => {this.initOrdinateur();});
}

initOrdinateur() {
  this.ordinateurSrv.getAll().subscribe((ordinateurs)=>{this.ordinateurs = ordinateurs;});
}