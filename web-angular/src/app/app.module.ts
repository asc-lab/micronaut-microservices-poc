import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PolicyListComponent } from './components/policy-list/policy-list.component';
import { PolicyCreateComponent } from './components/policy-create/policy-create.component';
import { PolicyDetailsComponent } from './components/policy-details/policy-details.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { AccountComponent } from './components/account/account.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    PolicyListComponent,
    PolicyCreateComponent,
    PolicyDetailsComponent,
    ProductListComponent,
    ProductDetailsComponent,
    AccountComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
