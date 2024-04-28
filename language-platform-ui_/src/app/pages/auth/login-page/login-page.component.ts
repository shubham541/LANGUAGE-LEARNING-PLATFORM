import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { UsernameLoginRequest } from '../../../model/usernameLoginRequest';
import { EmailLoginRequest } from '../../../model/emailLoginRequest';
import { AuthService } from '../../../../services/auth.service';
import { CustomValidators } from '../../../validators/validator';
import { PopupService } from '../../../../services/popup.service';
import { PayloadTokenResponse } from '../../../model/models';

enum LoginMode {
  username,
  email
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit, OnDestroy {

  unFormGroup!: FormGroup;
  emFormGroup!: FormGroup;
  console = console;
  hide = true;
  currIndex = 0;
  private readonly unsubscribe$ = new Subject<void>();
  loading = false;

  get usernameRequest(): UsernameLoginRequest {
    return {
      username: this.unFormGroup.get('username')?.value,
      password: this.unFormGroup.get('password')?.value
    };
  }

  get emailRequest(): EmailLoginRequest {
    return {
      email: this.emFormGroup.get('email')?.value,
      password: this.emFormGroup.get('password')?.value
    };
  }

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private validator: CustomValidators,
    private popupService: PopupService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.unFormGroup = this.fb.group({
      username: ['', this.validator.usernameValidator],
      password: ['', this.validator.passwordValidator],
    });

    this.emFormGroup = this.fb.group({
      email: ['', this.validator.emailValidator],
      password: ['', this.validator.passwordValidator],
    });
  }

  loginUser(index: number): void {
    const formGroup = index == 0 ? this.unFormGroup : this.emFormGroup;
    if (!formGroup.invalid) {
      this.loading = true;
      const futureResult = index == 0 ? this.authService.loginWithUsernameUsingPOST(this.usernameRequest) :
        this.authService.loginWithEmailUsingPOST(this.emailRequest);
      formGroup.reset();
      futureResult
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe({
          next: (response) => this.handleResponse(response),
          error: (error) => this.handleError(error)
        });
    }
  }

  handleResponse(response: PayloadTokenResponse) {
    this.loading = false;
    if (response.data?.token) {
      this.router.navigate(['home']);
    } else {
      this.popupService.showErrorMessage('Server Error');
    }
  }

  handleError(response: any) {
    this.loading = false;
    this.console.log(response);
    let message;
    if (response?.error?.status == 400) {
      message = response.error?.error;
    } else {
      message = response.error?.message;
    }
    this.popupService.showErrorMessage(message ?? "Server Error")
  }

  onTabChanged($event: any): void {
    this.currIndex = $event.index;
    this.unFormGroup.reset();
    this.emFormGroup.reset();
    this.hide = true;
  }

  isValid(formGroup: FormGroup, controlName: string): boolean {
    return this.validator.isValid(formGroup, controlName);
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }


}
