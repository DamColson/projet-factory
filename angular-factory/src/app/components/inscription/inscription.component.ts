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
import { CompteService } from '../../services/compte.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './inscription.component.html',
  styleUrl: './inscription.component.css',
})
export class InscriptionComponent {
  form!: FormGroup;

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
    });
  }

  submit() {
    let obj = {
      login: this.form.get('login')?.value,
      password: this.form.get('passwordGroup.password')?.value,
    };
    this.compteSrv.inscription(obj).subscribe((data) => {
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
}
