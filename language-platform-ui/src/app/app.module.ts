import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/auth/login-page/login-page.component';
import { ToolBarComponent } from './pages/base-wrapper/tool-bar/tool-bar.component';
import { DrawerComponent } from './pages/base-wrapper/drawer/drawer.component';
import { DateAgoPipe } from './pipes/date-ago.pipe';
import { AngularMaterialModule } from './shared/material.module';
import { ResetPasswordComponent } from './pages/auth/reset-password/reset-password.component';
import { SignUpPageComponent } from './pages/auth/sign-up-page/sign-up-page.component';
import { BaseWrapperComponent } from './pages/base-wrapper/base-wrapper.component';
import { ConfirmDialogComponent } from './shared/dialog/confirm-dialog/confirm-dialog.component';
import { TokenInterceptorService } from './shared/token-interceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { JwtModule } from '@auth0/angular-jwt';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { UserProfileComponent } from './pages/home/user-profile/user-profile.component';
import { HomePageComponent } from './pages/home/home-page/home-page.component';
import {MatAccordion, MatExpansionPanel, MatExpansionPanelTitle} from "@angular/material/expansion";
import { LanguageComponent } from './pages/home/language/language.component';
import { TranscriptsComponent } from './pages/home/transcripts/transcripts.component';

export function tokenGetter() {
  return localStorage.getItem("token");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    ToolBarComponent,
    DrawerComponent,
    DateAgoPipe,
    BaseWrapperComponent,
    ResetPasswordComponent,
    ConfirmDialogComponent,
    SignUpPageComponent,
    UserProfileComponent,
    HomePageComponent,
    LanguageComponent,
    TranscriptsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularMaterialModule,
    InfiniteScrollModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["example.com"],
        disallowedRoutes: ["http://example.com/examplebadroute/"],
      },
    }),
    MatAccordion,
    MatExpansionPanel,
    MatExpansionPanelTitle
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
