import { Location } from '@angular/common';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { ForgotPasswordRequest } from '../../../model/forgotPasswordRequest';
import { PopupService } from '../../../services/popup.service';
import { CustomValidators } from '../../../validators/validator';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit, AfterViewInit {

  username?: string;
  securityQn?: string;
  formGroup!: FormGroup;
  usernameForm!: FormGroup;
  loading = true;
  fromLogin = false;

  private readonly unsubs = new Subject<void>();

  get currUser(): string {
    const username = this.usernameForm?.get('username')?.value;
    if (username) {
      return username;
    }
    return this.authService.getCurrentUser();
  }

  get apiRequest(): ForgotPasswordRequest {
    return {
      newPassword: this.field('password'),
      securityAnswer: this.field('securityAns')
    };
  }

  constructor(
    private authService: AuthService,
    private popup: PopupService,
    private fb: FormBuilder,
    private validators: CustomValidators,
    private loc: Location
  ) { }

  ngOnInit(): void {
    this.fromLogin = !this.currUser;
    this.formGroup = this.fb.group({
      securityAns: ['', this.validators.securityAnsValidator],
      password: ['', this.validators.passwordValidator],
      confirmPassword: ['', this.validators.passwordValidator]
    });
    this.usernameForm = this.fb.group({
      username: ['', this.validators.usernameValidator]
    });
  }

  goBack() {
    this.loc.back();
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      if (!this.fromLogin) {
        this.fetchSecurityQn();
      }
    });
  }

  public fetchSecurityQn() {
    this.securityQn = undefined;
    this.loading = true;
    this.authService.fetchSecurityQnUsingGET(this.currUser)
      .pipe(takeUntil(this.unsubs))
      .subscribe(({
        next: (pyld: any) => {
          this.loading = false;
          if (pyld?.data) {
            this.securityQn = pyld?.data;
          }
        },
        error: (err) => this.popup.showError(err)
      }));
  }

  private field(name: string): string {
    return this.formGroup.get(name)?.value ?? '';
  }

  onReset() {
    let message;
    if (this.formGroup.valid) {
      if (this.field('password') === this.field('confirmPassword')) {
        this.loading = true;
        this.authService.forgotPasswordUsingPOST(this.apiRequest, this.currUser)
          .pipe(takeUntil(this.unsubs))
          .subscribe({
            next: (pyld) => {
              this.loading = false;
              this.formGroup.reset();
              if (pyld.data) {
                this.popup.showMessage('Password was updated!');
              } else {
                this.popup.showErrorMessage('Password couldn\'t be updated!');
              }
            },
            error: (err) => {
              this.formGroup.reset();
              this.loading = false;
              this.popup.showError(err);
            }
          });
      } else {
        message = 'both passwords should be same';
      }
    } else {
      message = 'Please input valid details';
    }
    if (message) {
      this.popup.showErrorMessage(message);
    }
  }

  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.unsubs.next();
    this.unsubs.complete();
  }


  isValid(formGroup: FormGroup, controlName: string): boolean {
    return this.validators.isValid(formGroup, controlName);
  }
}
