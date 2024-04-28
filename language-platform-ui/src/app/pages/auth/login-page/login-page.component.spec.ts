import { Component, DebugElement } from '@angular/core';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { } from 'jasmine';
import { of, throwError } from 'rxjs';
import { AngularMaterialModule } from 'src/app/material/material.module';
import { PayloadTokenResponse } from 'src/app/model/payloadTokenResponse';
import { TweetAppAuthService } from 'src/app/services/auth/tweet-app-auth.service';
import { PopupService } from 'src/app/services/popup.service';
import { CustomValidators } from 'src/app/validators/validator';
import { LoginPageComponent } from './login-page.component';

class DummyComponent { }

describe('LoginPageComponent', () => {
  let component: LoginPageComponent;
  let fixture: ComponentFixture<LoginPageComponent>;
  let compiled: any;
  // const formBuilderSpy: jasmine.SpyObj<FormBuilder> = jasmine.createSpyObj('FormBuilder', ['group']);
  const validatorSpy = jasmine.createSpyObj('CustomValidators', ['isValid']);
  const popupSpy: jasmine.SpyObj<PopupService> = jasmine.createSpyObj('PopupService', ['showErrorMessage']);
  let navigateSpy: jasmine.Spy<any>;
  let router: Router;
  const authServiceSpy: jasmine.SpyObj<TweetAppAuthService> = jasmine.createSpyObj('TweetAppAuthService', ['loginWithUsernameUsingPOST', 'loginWithEmailUsingPOST']);

  const successToken: PayloadTokenResponse = {
    data: {
      token: "some-token"
    }
  };

  const successNoToken: PayloadTokenResponse = {
    data: {
      token: undefined
    }
  };

  const badRequestResponse: PayloadTokenResponse = {
    error: {
      error: 'some-error',
      status: 400
    }
  };

  const errorReponse: PayloadTokenResponse = {
    error: {
      message: 'Internal Server Error',
      status: 500
    }
  };

  const errorNoMessageReponse: PayloadTokenResponse = {
    error: {
      status: 500
    }
  };

  const makeUsernameLogin = () => {
    component.unFormGroup.get('username')?.setValue('uname1');
    component.unFormGroup.get('password')?.setValue('password123');
    compiled.querySelector('#login-um-btn').click();
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginPageComponent],
      imports: [
        AngularMaterialModule,
        ReactiveFormsModule,
        RouterTestingModule.withRoutes([
          { path: 'home', component: DummyComponent }
        ])
      ],
      providers: [
        FormBuilder,
        { provide: TweetAppAuthService, useValue: authServiceSpy },
        // { provide: FormBuilder, useValue: formBuilderSpy },
        { provide: CustomValidators, useValue: validatorSpy },
        { provide: PopupService, useValue: popupSpy },
        // { provide: Router, useValue: routerSpy },
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    router = TestBed.inject(Router);
    navigateSpy = spyOn(router, 'navigate');
    fixture = TestBed.createComponent(LoginPageComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement;
    fixture.detectChanges();
    popupSpy.showErrorMessage.calls.reset();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show form content', () => {
    const formContent = compiled.querySelector('.form-content');
    expect(formContent).toBeTruthy();
    expect(formContent?.querySelector('#username-tab-form')).toBeTruthy();
    expect(formContent?.querySelector('#email-tab-form')).toBeNull();
  });

  it('should login successfully with username and navigate to home', () => {
    authServiceSpy.loginWithUsernameUsingPOST.and.callFake((_) => of(successToken));
    makeUsernameLogin();
    expect(compiled.querySelector('#login-um-btn').disabled).toBeFalse();
    expect(navigateSpy).toHaveBeenCalledWith(['home']);
  });

  it('should show server error modal when token is undefined', () => {
    authServiceSpy.loginWithUsernameUsingPOST.and.callFake((_) => of(successNoToken));
    makeUsernameLogin();
    expect(compiled.querySelector('#login-um-btn').disabled).toBeFalse();
    expect(popupSpy.showErrorMessage).toHaveBeenCalledWith('Server Error');
  });

  it('should show server error modal on server error with bad request', () => {
    authServiceSpy.loginWithUsernameUsingPOST.and.callFake((_) => throwError(() => badRequestResponse));
    makeUsernameLogin();
    expect(compiled.querySelector('#login-um-btn').disabled).toBeFalse();
    expect(popupSpy.showErrorMessage).toHaveBeenCalledWith('some-error');
  });

  it('should show server error modal on internal server error', () => {
    authServiceSpy.loginWithUsernameUsingPOST.and.callFake((_) => throwError(() => errorReponse));
    makeUsernameLogin();
    expect(compiled.querySelector('#login-um-btn').disabled).toBeFalse();
    expect(popupSpy.showErrorMessage).toHaveBeenCalledWith('Internal Server Error');
  });

  it('should show server error modal on internal server error', () => {
    authServiceSpy.loginWithUsernameUsingPOST.and.callFake((_) => throwError(() => errorNoMessageReponse));
    makeUsernameLogin();
    expect(compiled.querySelector('#login-um-btn').disabled).toBeFalse();
    expect(popupSpy.showErrorMessage).toHaveBeenCalledWith('Server Error');
  });

  xit('should change the tab when email is clicked', fakeAsync(() => {
    console.log(compiled.querySelectorAll('.mat-tab-label'));
    fixture.debugElement.queryAll(By.css('.mat-tab-label'))[1].nativeElement.click();
    fixture.detectChanges();
    tick(599);
    fixture.detectChanges();

    const formContent = compiled.querySelector('.form-content');
    expect(formContent?.querySelector('#username-tab-form')).toBeNull();
    expect(formContent?.querySelector('#email-tab-form')).toBeTruthy();
  }));
});
