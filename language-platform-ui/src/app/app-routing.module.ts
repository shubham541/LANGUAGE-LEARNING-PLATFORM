import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './pages/auth/login-page/login-page.component';
import { RouteGuardService } from './shared/route-guard.service';
import { ResetPasswordComponent } from './pages/auth/reset-password/reset-password.component';
import { SignUpPageComponent } from './pages/auth/sign-up-page/sign-up-page.component';
import { BaseWrapperComponent } from './pages/base-wrapper/base-wrapper.component';
import { AuthGuardService } from './shared/auth-guard.service';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';

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
    {
      path: '', component: BaseWrapperComponent,
      canActivate: [AuthGuardService],
      children: [
        {
          path: 'profile/:username',
          component: UserProfileComponent,
          canActivate: [AuthGuardService],
          pathMatch: 'full'
        },
        {
          path: 'reset', component: ResetPasswordComponent,
          canActivate: [AuthGuardService]
        },
      ]
    },
    { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
