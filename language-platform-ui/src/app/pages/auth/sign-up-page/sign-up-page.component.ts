import {formatDate, Location} from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { RegisterRequest } from '../../../model/registerRequest';
import { PopupService } from '../../../services/popup.service';
import { CustomValidators } from '../../../validators/validator';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss']
})
export class SignUpPageComponent implements OnInit, OnDestroy {

  formGroup!: FormGroup;
  stepOneFG!: FormGroup;
  stepTwoFG!: FormGroup;
  stepThreeFG!: FormGroup;
  stepFourFG!: FormGroup;
  loading = false;
  nowDate = new Date();
  private readonly unsubs = new Subject<void>();

  genderOptions = [
    { key: 'Male', value: RegisterRequest.GenderEnum.Male },
    { key: 'Female', value: RegisterRequest.GenderEnum.Female },
    { key: 'Prefer not to say', value: RegisterRequest.GenderEnum.PreferNotToSay },
  ];

  securityQNOptions = [
    'In what city were you born ?',
    'What is the name of your favorite pet ?',
    'What is your mother\'s maiden name?',
    'What high school did you attend ?',
    'What is the name of your first school ?',
    'What was the make of your first car ?',
    'What was your favorite food as a child ?',
    'Where did you meet your spouse ?'
  ];

  get apiRequest(): RegisterRequest {
    return {
      bio: this.stepThreeFG.get('bio')?.value,
      contactNumber: this.stepOneFG.get('phoneNumber')?.value,
      dob: formatDate(this.stepThreeFG.get('dob')?.value, 'dd-MM-yyyy', 'en-US'),
      email: this.stepOneFG.get('email')?.value,
      firstName: this.stepTwoFG.get('firstName')?.value,
      lastName: this.stepTwoFG.get('lastName')?.value,
      gender: this.stepTwoFG.get('gender')?.value,
      password: this.stepFourFG.get('password')?.value,
      securityAnswer: this.stepFourFG.get('securityAns')?.value,
      securityQn: this.stepFourFG.get('securityQn')?.value,
      username: this.stepOneFG.get('username')?.value
    };
  }

  constructor(
    private fb: FormBuilder,
    private validators: CustomValidators,
    private location: Location,
    private authService: AuthService,
    private popup: PopupService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.stepOneFG = this.fb.group({
      username: ['', this.validators.usernameValidator],
      email: ['', this.validators.emailValidator],
      phoneNumber: ['', this.validators.phoneValidator]
    });
    this.stepTwoFG = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(24)]],
      lastName: ['', [Validators.minLength(3), Validators.maxLength(24)]],
      gender: ['', [Validators.required]]
    });
    this.stepThreeFG = this.fb.group({
      bio: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(120)]],
      dob: [''],
    });
    this.stepFourFG = this.fb.group({
      password: ['', this.validators.passwordValidator],
      confirmPassword: ['', this.validators.passwordValidator],
      securityQn: ['', [Validators.required]],
      securityAns: ['', this.validators.securityAnsValidator],
      terms: [false, [Validators.requiredTrue]]
    });

    this.formGroup = this.fb.group({
      stepOne: this.stepOneFG,
      stepTwp: this.stepTwoFG,
      stepThree: this.stepThreeFG,
      stepFour: this.stepFourFG
    });
  }

  goBack() {
    this.location.back();
  }

  isValid(formGroup: FormGroup, controlName: string): boolean {
    return this.validators.isValid(formGroup, controlName);
  }

  isValidCheckbox() {
    const control = this.stepFourFG.get('terms');
    return !(!!control?.value || !control?.touched);
  }

  registerUser() {
    if (this.formGroup.valid) {
      this.loading = true;
      this.authService.registerUserUsingPOST(this.apiRequest)
        .pipe(takeUntil(this.unsubs))
        .subscribe({
          next: (_) => this.handleResponse(),
          error: (err) => {
            this.loading = false;
            this.popup.showError(err);
          }
        });
    } else {
      this.popup.showErrorMessage('Please check the input!');
    }
  }

  handleResponse(): void {
    this.loading = false;
    this.router.navigate(['/home']);
  }

  ngOnDestroy(): void {
    this.unsubs.next();
    this.unsubs.complete();
  }
}
