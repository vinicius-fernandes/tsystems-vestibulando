import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})

export class ConfirmDialogComponent {
  title: string = ''
  message: string = ''

  constructor( public dialogRef: MatDialogRef<ConfirmDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmDialogModel) {
      this.title = data.title
      this.message = data.message
    }

    onConfirm() {
      this.dialogRef.close(true)
    }

    onDismiss() {
      this.dialogRef.close(false)
    }
}


export class ConfirmDialogModel {
  constructor( public title: string, public message: string ) { }
}