import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuardService } from './core/guards/auth-guard.service';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/auth' },
  { path: 'auth', component: LoginComponent},
  { path: 'home', component: HomeComponent , canActivate: [AuthGuardService] },
];
