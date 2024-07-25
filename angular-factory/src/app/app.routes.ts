import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { anonymousGuard } from './guards/anonymous.guard';
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MaFormationComponent } from './components/ma-formation/ma-formation.component';
import { MesBlocsComponent } from './components/mes-blocs/mes-blocs.component';
import { OrdinateurComponent } from './components/ordinateur/ordinateur/ordinateur.component';
import { OrdinateurEditComponent } from './components/ordinateur/ordinateur-edit/ordinateur-edit.component';
import { VideoprojecteurComponent } from './components/videoprojecteur/videoprojecteur/videoprojecteur.component';
import { VideoprojecteurEditComponent } from './components/videoprojecteur/videoprojecteur-edit/videoprojecteur-edit.component';
import { BlocComponent } from './components/bloc/bloc/bloc.component';
import { BlocEditComponent } from './components/bloc/bloc-edit/bloc-edit.component';
import { CompetenceComponent } from './components/competence/competence/competence.component';
import { CompetenceEditComponent } from './components/competence/competence-edit/competence-edit.component';
import { FormationComponent } from './components/formation/formation/formation.component';
import { FormationEditComponent } from './components/formation/formation-edit/formation-edit.component';
import { MatiereComponent } from './components/matiere/matiere/matiere.component';
import { MatiereEditComponent } from './components/matiere/matiere-edit/matiere-edit.component';
import { SalleComponent } from './components/salle/salle/salle.component';
import { SalleEditComponent } from './components/salle/salle-edit/salle-edit.component';
import { CompteComponent } from './components/compte/compte/compte.component';
import { CompteEditComponent } from './components/compte/compte-edit/compte-edit.component';
import { authGuard } from './guards/auth.guard';
import { StagiaireComponent } from './components/stagiaire/stagiaire/stagiaire.component';
import { StagiaireEditComponent } from './components/stagiaire/stagiaire-edit/stagiaire-edit.component';
import { FormateurComponent } from './components/formateur/formateur/formateur.component';
import { FormateurEditComponent } from './components/formateur/formateur-edit/formateur-edit.component';
import { TechnicienEditComponent } from './components/technicien/technicien-edit/technicien-edit.component';
import { TechnicienComponent } from './components/technicien/technicien/technicien.component';
import { GestionnaireEditComponent } from './components/gestionnaire/gestionnaire-edit/gestionnaire-edit.component';
import { GestionnaireComponent } from './components/gestionnaire/gestionnaire/gestionnaire.component';
import { FormateurCompetencesEditComponent } from './components/formateur/formateur-competences-edit/formateur-competences-edit.component';
import { MatiereCompetencesEditComponent } from './components/matiere/matiere-competences-edit/matiere-competences-edit.component';
import { FormationBlocsEditComponent } from './components/formation/formation-blocs-edit/formation-blocs-edit.component';
import { FormationStagiairesEditComponent } from './components/formation/formation-stagiaires-edit/formation-stagiaires-edit.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'maformation',
    component: MaFormationComponent,
    canActivate: [authGuard],
  },
  { path: 'mesblocs', component: MesBlocsComponent, canActivate: [authGuard] },
  {
    path: 'ordinateur',
    component: OrdinateurComponent,
    canActivate: [authGuard],
  },
  {
    path: 'ordinateur/edit',
    component: OrdinateurEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'ordinateur/edit/:id',
    component: OrdinateurEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'videoprojecteur',
    component: VideoprojecteurComponent,
    canActivate: [authGuard],
  },
  {
    path: 'videoprojecteur/edit',
    component: VideoprojecteurEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'videoprojecteur/edit/:id',
    component: VideoprojecteurEditComponent,
    canActivate: [authGuard],
  },
  { path: 'bloc', component: BlocComponent, canActivate: [authGuard] },
  { path: 'bloc/edit', component: BlocEditComponent, canActivate: [authGuard] },
  {
    path: 'bloc/edit/:id',
    component: BlocEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'competence',
    component: CompetenceComponent,
    canActivate: [authGuard],
  },
  {
    path: 'competence/edit',
    component: CompetenceEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'competence/edit/:id',
    component: CompetenceEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formation',
    component: FormationComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formation/edit',
    component: FormationEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formation/edit/:id',
    component: FormationEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formation/:id/blocs',
    component: FormationBlocsEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formation/:id/stagiaires',
    component: FormationStagiairesEditComponent,
    canActivate: [authGuard],
  },
  { path: 'matiere', component: MatiereComponent, canActivate: [authGuard] },
  {
    path: 'matiere/edit',
    component: MatiereEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'matiere/edit/:id',
    component: MatiereEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'matiere/:id/competences',
    component: MatiereCompetencesEditComponent,
    canActivate: [authGuard],
  },
  { path: 'salle', component: SalleComponent, canActivate: [authGuard] },
  {
    path: 'salle/edit',
    component: SalleEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'salle/edit/:id',
    component: SalleEditComponent,
    canActivate: [authGuard],
  },
  { path: 'compte', component: CompteComponent, canActivate: [authGuard] },
  {
    path: 'compte/edit',
    component: CompteEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'compte/edit/:id',
    component: CompteEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'stagiaire',
    component: StagiaireComponent,
    canActivate: [authGuard],
  },
  {
    path: 'stagiaire/edit',
    component: StagiaireEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'stagiaire/edit/:id',
    component: StagiaireEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formateur',
    component: FormateurComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formateur/edit',
    component: FormateurEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formateur/edit/:id',
    component: FormateurEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'formateur/:id/competences',
    component: FormateurCompetencesEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'technicien',
    component: TechnicienComponent,
    canActivate: [authGuard],
  },
  {
    path: 'technicien/edit',
    component: TechnicienEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'technicien/edit/:id',
    component: TechnicienEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'gestionnaire',
    component: GestionnaireComponent,
    canActivate: [authGuard],
  },
  {
    path: 'gestionnaire/edit',
    component: GestionnaireEditComponent,
    canActivate: [authGuard],
  },
  {
    path: 'gestionnaire/edit/:id',
    component: GestionnaireEditComponent,
    canActivate: [authGuard],
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [anonymousGuard] },
  { path: '**', component: PageNotFoundComponent },
];
