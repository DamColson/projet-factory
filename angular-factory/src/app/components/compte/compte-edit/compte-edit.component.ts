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

  constructor(
    private compteSrv: CompteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = new FormGroup({
      login: new FormControl('', Validators.required),
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
    // this.filiereSrv.getAll().subscribe((filieres) => {
    //   this.filieres = filieres;
    // });
    //this.OrdinateurObservable = this.ordinateurSrv.getAll();

    this.activatedRoute.params.subscribe((params) => {
      if (params['id']) {
        this.compteSrv.getById(params['id']).subscribe((compte) => {
          this.compte = compte;
        });
      }
    });
  }

  submit() {
    let obj = {
      login: this.form.get('login')?.value,
      password: this.form.get('passwordGroup.password')?.value,
      role: this.form.get('role')?.value,
    };
    if (this.compte.id) {
      this.compteSrv.update(this.compte).subscribe((compte) => {
        this.router.navigateByUrl('/compte?q=update&id=' + compte.id);
      });
    } else {
      this.compteSrv.create(this.compte).subscribe((compte) => {
        this.router.navigateByUrl('/compte?q=create&id=' + compte.id);
      });
    }
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
