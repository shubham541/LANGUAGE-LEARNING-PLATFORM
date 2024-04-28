import { ComponentType } from '@angular/cdk/portal';
import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor(
    private snackbar: MatSnackBar,
    private dialog: MatDialog
  ) { }

  showMessage(message: string, panelClass = ['mat-toolbar', 'mat-accent']) {
    this.snackbar.open(message, "OK", {
      duration: 5000,
      verticalPosition: 'top',
      panelClass: panelClass
    });
  }

  showErrorMessage(message: string) {
    this.showMessage(message, ['mat-toolbar', 'mat-warn']);
  }

  showError(error: any) {
    let message: string | null = null;

    if (error?.error?.message) {
      message = error?.error?.message;
    }
    if (error?.error?.error) {
      message = `${message}${message ? ' - ' : ''}${error?.error?.error ?? ''}`;
    }

    this.showErrorMessage(message || 'Internal Server Error');
  }

  showDialog<T, D>(component: ComponentType<T>, data?: D): MatDialogRef<T, any> {
    return this.dialog.open(component, {
      data: data
    });
  }
}
