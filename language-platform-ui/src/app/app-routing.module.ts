import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './pages/auth/login-page/login-page.component';
import { RouteGuardService } from './shared/route-guard.service';
import { ResetPasswordComponent } from './pages/auth/reset-password/reset-password.component';
import { SignUpPageComponent } from './pages/auth/sign-up-page/sign-up-page.component';
import { BaseWrapperComponent } from './pages/base-wrapper/base-wrapper.component';
import { AuthGuardService } from './shared/auth-guard.service';
import { UserProfileComponent } from './pages/home/user-profile/user-profile.component';
import {HomePageComponent} from "./pages/home/home-page/home-page.component";
import {LanguageComponent} from "./pages/home/language/language.component";
import {QuizComponent} from "./pages/home/quiz/quiz.component";

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
          pathMatch: 'full'
        },
        {
          path: 'home',
          component: HomePageComponent
        },
        {
          path: 'languages/:code',
          component: LanguageComponent,
          pathMatch: 'full'
        },
        {
          path: 'quiz/:code',
          component: QuizComponent,
          pathMatch: 'full'
        },
        {
          path: 'reset', component: ResetPasswordComponent,
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
