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
import { Router } from '@angular/router';

@Component({
  selector: 'app-compte-edit',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './compte-edit.component.html',
  styleUrl: './compte-edit.component.css',
})
export class CompteEditComponent {
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
}
