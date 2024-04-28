import { Injectable } from "@angular/core";
import { FormGroup, Validators } from "@angular/forms";

@Injectable({
    providedIn: 'root'
})
export class CustomValidators {
    // username
    private _usernameValidator = [Validators.required, Validators.minLength(5), Validators.maxLength(12)];
    // password
    private _passwordValidator = [Validators.required, Validators.minLength(8), Validators.maxLength(12)];
    // email
    private _emailValidator = [Validators.required, Validators.email];

    private _phoneValidator = [Validators.required, Validators.minLength(10), Validators.maxLength(10)];

    private _messageValidator = [Validators.required, Validators.minLength(10), Validators.maxLength(144)];

    private _tagValidator = [Validators.maxLength(50)];

    private _securityAnsValidator = [Validators.required, Validators.minLength(3)];

    public get usernameValidator(): Array<any> {
        return this._usernameValidator.slice();
    }

    public get phoneValidator(): Array<any> {
        return this._phoneValidator.slice();
    }

    public get securityAnsValidator(): Array<any> {
        return this._securityAnsValidator.slice();
    }

    public get passwordValidator(): Array<any> {
        return this._passwordValidator.slice();
    }

    public get emailValidator(): Array<any> {
        return this._emailValidator.slice();
    }

    public get messageValidator(): Array<any> {
        return this._messageValidator.slice();
    }

    public get tagValidator(): Array<any> {
        return this._tagValidator.slice();
    }

    public isValid(formGroup: FormGroup, controlName: string): boolean {
        const control = formGroup.get(controlName);
        if (!control) {
            return false;
        }
        return control.invalid && control.dirty || control.touched;
    }

}