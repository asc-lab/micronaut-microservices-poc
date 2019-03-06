import {NgModule} from '@angular/core';
import {MatButtonModule, MatCardModule, MatDialogModule, MatIconModule, MatTableModule} from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatCardModule,
    MatTableModule
  ],
  exports: [
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatCardModule,
    MatTableModule
  ],
  declarations: []
})
export class MaterialModule {
}
