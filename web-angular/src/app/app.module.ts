import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {FormlyModule} from '@ngx-formly/core';
import {FormlyMaterialModule} from "@ngx-formly/material";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PolicyListComponent} from './components/policy-list/policy-list.component';
import {PolicyCreateComponent} from './components/policy-create/policy-create.component';
import {PolicyDetailsComponent} from './components/policy-details/policy-details.component';
import {ProductListComponent} from './components/product-list/product-list.component';
import {ProductDetailsComponent} from './components/product-details/product-details.component';
import {AccountComponent} from './components/account/account.component';
import {HomeComponent} from './components/home/home.component';
import {AuthService} from "./shared/auth-service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from './components/material/material.module';
import {PolicyService} from "./shared/policy-service";
import {ProductService} from "./shared/product-service";
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import {ErrorDialogService} from "./components/error-dialog/error-dialog.service";
import {AuthInterceptor} from "./shared/auth-interceptor";
import {FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
  declarations: [
    AppComponent,
    PolicyListComponent,
    PolicyCreateComponent,
    PolicyDetailsComponent,
    ProductListComponent,
    ProductDetailsComponent,
    AccountComponent,
    HomeComponent,
    ErrorDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FlexLayoutModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialModule,
    FormlyMaterialModule,
    FormlyModule.forRoot({
      validationMessages: [
        {name: 'required', message: 'This field is required'},
      ]
    })
  ],
  providers: [
    AuthService,
    PolicyService,
    ProductService,
    ErrorDialogService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  entryComponents: [
    ErrorDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
