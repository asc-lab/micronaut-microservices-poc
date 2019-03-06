import {Injectable} from '@angular/core';
import {MatDialog} from '@angular/material';
import {ErrorDialogComponent} from './error-dialog.component';

@Injectable()
export class ErrorDialogService {

  constructor(public dialog: MatDialog) {
  }

  openDialog(data): void {
    this.dialog.open(ErrorDialogComponent, {
      width: '300px',
      data: data
    });
  }
}
