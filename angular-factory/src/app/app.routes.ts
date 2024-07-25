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
import { BlocAffichageComponent } from './components/bloc/bloc-affichage/bloc-affichage.component';
import { techGuard } from './guards/tech.guard';
import { gestGuard } from './guards/gest.guard';
import { stagGuard } from './guards/stag.guard';
import { formGuard } from './guards/form.guard';
import { authGuard } from './guards/auth.guard';
import { adminGuard } from './guards/admin.guard';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'maformation',
    component: MaFormationComponent,
    canActivate: [stagGuard],
  },
  { path: 'mesblocs', component: MesBlocsComponent, canActivate: [formGuard] },
  {
    path: 'ordinateur',
    component: OrdinateurComponent,
    canActivate: [techGuard],
  },
  {
    path: 'ordinateur/edit',
    component: OrdinateurEditComponent,
    canActivate: [techGuard],
  },
  {
    path: 'ordinateur/edit/:id',
    component: OrdinateurEditComponent,
    canActivate: [techGuard],
  },
  {
    path: 'videoprojecteur',
    component: VideoprojecteurComponent,
    canActivate: [techGuard],
  },
  {
    path: 'videoprojecteur/edit',
    component: VideoprojecteurEditComponent,
    canActivate: [techGuard],
  },
  {
    path: 'videoprojecteur/edit/:id',
    component: VideoprojecteurEditComponent,
    canActivate: [techGuard],
  },
  { path: 'bloc', component: BlocComponent, canActivate: [gestGuard] },
  {
    path: 'bloc/details/:id',
    component: BlocAffichageComponent,
    canActivate: [authGuard],
  },
  { path: 'bloc/edit', component: BlocEditComponent, canActivate: [gestGuard] },
  {
    path: 'bloc/edit/:id',
    component: BlocEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'competence',
    component: CompetenceComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'competence/edit',
    component: CompetenceEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'competence/edit/:id',
    component: CompetenceEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'formation',
    component: FormationComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'formation/edit',
    component: FormationEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'formation/edit/:id',
    component: FormationEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'formation/:id/blocs',
    component: FormationBlocsEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'formation/:id/stagiaires',
    component: FormationStagiairesEditComponent,
    canActivate: [gestGuard],
  },
  { path: 'matiere', component: MatiereComponent, canActivate: [gestGuard] },
  {
    path: 'matiere/edit',
    component: MatiereEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'matiere/edit/:id',
    component: MatiereEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'matiere/:id/competences',
    component: MatiereCompetencesEditComponent,
    canActivate: [gestGuard],
  },
  { path: 'salle', component: SalleComponent, canActivate: [gestGuard] },
  {
    path: 'salle/edit',
    component: SalleEditComponent,
    canActivate: [gestGuard],
  },
  {
    path: 'salle/edit/:id',
    component: SalleEditComponent,
    canActivate: [gestGuard],
  },
  { path: 'compte', component: CompteComponent, canActivate: [adminGuard] },
  {
    path: 'compte/edit',
    component: CompteEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'compte/edit/:id',
    component: CompteEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'stagiaire',
    component: StagiaireComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'stagiaire/edit',
    component: StagiaireEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'stagiaire/edit/:id',
    component: StagiaireEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'formateur',
    component: FormateurComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'formateur/edit',
    component: FormateurEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'formateur/edit/:id',
    component: FormateurEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'formateur/:id/competences',
    component: FormateurCompetencesEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'technicien',
    component: TechnicienComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'technicien/edit',
    component: TechnicienEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'technicien/edit/:id',
    component: TechnicienEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'gestionnaire',
    component: GestionnaireComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'gestionnaire/edit',
    component: GestionnaireEditComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'gestionnaire/edit/:id',
    component: GestionnaireEditComponent,
    canActivate: [adminGuard],
  },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [anonymousGuard] },
  { path: '**', component: PageNotFoundComponent },
];
