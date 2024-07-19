import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { anonymousGuard } from './guards/anonymous.guard';
import { LoginComponent } from './components/login/login.component';
import { InscriptionComponent } from './components/inscription/inscription.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [anonymousGuard] },
  { path: 'inscription', component: InscriptionComponent },
  { path: '**', component: PageNotFoundComponent },
];
