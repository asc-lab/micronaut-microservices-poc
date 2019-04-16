import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AccountComponent} from "./components/account/account.component";
import {ProductListComponent} from "./components/product-list/product-list.component";
import {ProductDetailsComponent} from "./components/product-details/product-details.component";
import {PolicyListComponent} from "./components/policy-list/policy-list.component";
import {PolicyDetailsComponent} from "./components/policy-details/policy-details.component";
import {PolicyCreateComponent} from "./components/policy-create/policy-create.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'account', component: AccountComponent},
  {path: 'products', component: ProductListComponent},
  {path: 'products/:productCode', component: ProductDetailsComponent},
  {path: 'policies', component: PolicyListComponent},
  {path: 'policies/:policyNumber', component: PolicyDetailsComponent},
  {path: 'policy/fromOffer/:offerNumber', component: PolicyCreateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
