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
import {
  ActivatedRoute,
  Router,
  RouterLink,
  RouterLinkActive,
} from '@angular/router';
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

  constructor(
    private compteSrv: CompteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = new FormGroup({
      login: new FormControl(''),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', Validators.required),
          confirmation: new FormControl(''),
        },
        this.passwordEtConfirmationEgaux
      ),
      role: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.compteSrv.getById(params['id']).subscribe((compte) => {
          this.compte = compte;
          this.selectedValue(this.compte.role);
        });
      }
    });
  }

  submit() {
    let resultLogin = '';
    let resultRole = '';

    if (this.form.get('role')?.value == '') {
      resultRole = <string>this.compte.role;
    } else {
      resultRole = this.form.get('role')?.value;
    }
    if (this.form.get('login')?.value == '') {
      resultLogin = <string>this.compte.login;
    } else {
      resultLogin = this.form.get('login')?.value;
    }
    if (this.compte.id) {
      let obj = {
        id: this.compte.id,
        login: resultLogin,
        password: this.form.get('passwordGroup.password')?.value,
        role: resultRole,
      };
      this.compteSrv.update(obj).subscribe((compte) => {
        this.router.navigateByUrl('/compte?q=update&id=' + this.compte.id);
      });
    } else {
      let obj = {
        login: this.form.get('login')?.value,
        password: this.form.get('passwordGroup.password')?.value,
        role: this.form.get('role')?.value,
      };
      this.compteSrv.create(obj).subscribe((compte) => {
        this.router.navigateByUrl('/compte?q=create&id=' + this.compte.id);
      });
    }
  }

  selectedValue(select: any) {
    const selectElement = document.getElementById('role') as HTMLSelectElement;
    selectElement.value = this.roles.indexOf(select) + ': ' + <string>select;
  }

  passwordEtConfirmationEgaux(
    control: AbstractControl
  ): null | ValidationErrors {
    return control.get('password')?.value == control.get('confirmation')?.value
      ? null
      : { passwordEtConfirmationNotEquals: true };
  }

  compareFn(f1: String, f2: String): boolean {
    return f1 && f2 ? f1 === f2 : false;
  }
}
