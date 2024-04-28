import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface ConfirmationData {
  title: string;
  subtitle: string;
  onlyOk?: boolean;
}

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent implements OnInit {

  get data(): ConfirmationData {
    return this._data;
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) private _data: ConfirmationData,
    private dialogRef: MatDialogRef<ConfirmDialogComponent>
  ) { }

  ngOnInit(): void {
  }

  onResult(result: boolean) {
    this.dialogRef.close({ result });
  }

}
