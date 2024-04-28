import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/auth/login-page/login-page.component';
import { RouteGuardService } from './shared/route-guard.service';
import { ResetPasswordComponent } from './pages/auth/reset-password/reset-password.component';
import { SignUpPageComponent } from './pages/auth/sign-up-page/sign-up-page.component';

export const routes: Routes = [
    {
        path: 'login', component: LoginPageComponent,
        canActivate: [RouteGuardService]
      },
      {
        path: 'forgot', component: ResetPasswordComponent,
        canActivate: [RouteGuardService]
      },
      {
        path: 'sign-up', component: SignUpPageComponent,
        canActivate: [RouteGuardService]
      },
      {
        path: '', redirectTo: 'home',
        pathMatch: 'full'
      },
];
