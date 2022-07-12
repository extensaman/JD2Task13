import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SubscriptionComponent} from "./view/subscription/subscription.component";

const routes: Routes = [
  {path: 'subscriptions', component: SubscriptionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
