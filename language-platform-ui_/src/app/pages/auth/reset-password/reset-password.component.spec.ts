import { } from 'jasmine';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';

import { ResetPasswordComponent } from './reset-password.component';
import { TweetAppAuthService } from 'src/app/services/auth/tweet-app-auth.service';
import { PopupService } from 'src/app/services/popup.service';
import { CustomValidators } from 'src/app/validators/validator';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/material/material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Payloadstring } from 'src/app/model/payloadstring';
import { Payloadboolean } from 'src/app/model/payloadboolean';

fdescribe('ResetPasswordComponent', () => {
  let component: ResetPasswordComponent;
  let compiled: any;
  let fixture: ComponentFixture<ResetPasswordComponent>;
  const validatorSpy = jasmine.createSpyObj('CustomValidators', ['isValid']);
  const popupSpy: jasmine.SpyObj<PopupService> = jasmine.createSpyObj('PopupService', ['showErrorMessage']);
  const authServiceSpy: jasmine.SpyObj<TweetAppAuthService>
    = jasmine.createSpyObj('TweetAppAuthService', ['fetchSecurityQnUsingGET', 'getCurrentUser', 'forgotPasswordUsingPOST']);
  const securityQnResp: Payloadstring = { data: 'Your Security Qn A?' };
  const forgotPassResp: Payloadboolean = { data: true };
  const forgotPassFalseResp: Payloadboolean = { data: false };

  beforeEach(async () => {
    authServiceSpy.fetchSecurityQnUsingGET.and.callFake(() => of(securityQnResp));
    authServiceSpy.forgotPasswordUsingPOST.and.callFake(() => of(forgotPassResp));
    await TestBed.configureTestingModule({
      declarations: [ResetPasswordComponent],
      imports: [
        AngularMaterialModule,
        ReactiveFormsModule,
        RouterTestingModule
      ],
      providers: [
        FormBuilder,
        { provide: TweetAppAuthService, useValue: authServiceSpy },
        { provide: CustomValidators, useValue: validatorSpy },
        { provide: PopupService, useValue: popupSpy },
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetPasswordComponent);
    component = fixture.componentInstance;
    compiled = fixture.nativeElement;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should reset password successfully', () => {
    authServiceSpy.getCurrentUser.and.returnValue('');
    component.formGroup.get('securityAns')?.setValue('my answer');
    component.formGroup.get('password')?.setValue('newpassword');
    component.formGroup.get('confirmPassword')?.setValue('newpassword');
    compiled.querySelector('button#resetBtn').click();
  })

  describe('from login page', () => {

    beforeEach(() => {
      authServiceSpy.getCurrentUser.and.returnValue('');
    });

    it('should show step 1 in the beginning', () => {
      const stepOne = compiled.querySelectorAll('.mat-step-header')[0];
      expect(stepOne).toBeTruthy();
      const fetchSecBtn = compiled.querySelector("button#fetchSecurityQnBtn");
      expect(fetchSecBtn).toBeTruthy();
      expect(fetchSecBtn.textContent).toEqual('Fetch Details');
    });

    it('should move to step 2 after clicking on fetch details', () => {
      component.formGroup.get('username')?.setValue('uname01');
      fixture.detectChanges();
      expect(compiled.querySelector("button#fetchSecurityQnBtn").disabled).toBeFalse();
      compiled.querySelector("button#fetchSecurityQnBtn").click();
      fixture.detectChanges();
      expect(compiled.querySelector('p#securityQn').textContent).toBe('Your Security Qn A?');
    });
  });
});
