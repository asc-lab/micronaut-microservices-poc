import {NgModule} from '@angular/core';
import {MatButtonModule, MatIconModule} from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule,
    MatIconModule
  ],
  exports: [
    MatButtonModule,
    MatIconModule
  ],
  declarations: []
})
export class MaterialModule {
}
