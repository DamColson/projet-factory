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
  compteStr = localStorage.getItem('compte');

  constructor(private authSrv: AuthService, private router: Router) {}

  connect() {
    this.authSrv.connect(this.login, this.password).subscribe({
      next: (compte) => {
        localStorage.setItem(
          'token',
          window.btoa(this.login + ':' + this.password)
        );
        localStorage.setItem('compte', JSON.stringify(compte));
        console.log(localStorage.getItem('compte'));
        this.compteStr = localStorage.getItem('compte');
        if (this.compteStr) {
          console.log(JSON.parse(this.compteStr).id);
        }
        this.router.navigateByUrl('/home');
      },
      error: (err) => {
        this.error = true;
        console.debug(err);
      },
    });
  }
}
