import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  login: string = '';
  password: string = '';
  error: boolean = false;

  constructor(private authSrv: AuthService, private router: Router) {}

  connect() {
    this.authSrv.connect(this.login, this.password).subscribe({
      //next=>on a une reponse
      next: (utilisateur) => {
        //on est sur que le couple login/password est correct
        //je stocke le couple dans le localStorage
        localStorage.setItem(
          'token',
          window.btoa(this.login + ':' + this.password)
        );
        localStorage.setItem('utilisateur', JSON.stringify(utilisateur));
        console.log(localStorage.getItem('utilisateur') + this.login);
        this.router.navigateByUrl('/home');
      },
      error: (err) => {
        this.error = true;
        console.debug(err);
      },
    });
  }
}
