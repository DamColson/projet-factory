import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Compte } from '../../model/compte';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css',
})
export class MenuComponent {
  constructor(private router: Router) {}

  get login(): string {
    let c: Compte = JSON.parse(localStorage.getItem('compte')!);
    return c.login!;
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('/home');
  }

  get logged(): boolean {
    return localStorage.getItem('token') != null;
  }

  cheminImage: any = '../assets/images/logo-texte.png';
}
