import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { CompteService } from '../../../services/compte.service';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { Compte } from '../../../model/compte';

@Component({
  selector: 'app-compte-edit',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    AsyncPipe,
    RouterLink,
    RouterLinkActive,
  ],
  templateUrl: './compte-edit.component.html',
  styleUrl: './compte-edit.component.css',
})
export class CompteEditComponent {
  form!: FormGroup;
  roles: String[] = [
    'ROLE_STAGIAIRE',
    'ROLE_FORMATEUR',
    'ROLE_TECHNICIEN',
    'ROLE_GESTIONNAIRE',
    'ROLE_ADMIN',
  ];
  compte: Compte = new Compte();

  constructor(private compteSrv: CompteService, private router: Router) {
    this.form = new FormGroup({
      login: new FormControl('', Validators.required),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', Validators.required),
          confirmation: new FormControl(''),
        },
        this.passwordEtConfirmationEgaux
      ),
      role: new FormControl(''),
    });
  }

  submit() {
    let obj = {
      login: this.form.get('login')?.value,
      password: this.form.get('passwordGroup.password')?.value,
    };
    this.compteSrv.create(obj).subscribe((data) => {
      this.router.navigateByUrl('/login');
    });
  }

  passwordEtConfirmationEgaux(
    control: AbstractControl
  ): null | ValidationErrors {
    return control.get('password')?.value == control.get('confirmation')?.value
      ? null
      : { passwordEtConfirmationNotEquals: true };
  }

  compareFn(f1: string, f2: string): boolean {
    return f1 && f2 ? f1 === f2 : false;
  }
}
