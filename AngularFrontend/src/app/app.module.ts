import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './view/header/header.component';
import {SubscriptionComponent} from './view/subscription/subscription.component';
import {SubscriptionService} from "./service/subscription.service";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatBadgeModule} from '@angular/material/badge';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SubscriptionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    FlexLayoutModule,
    MatBadgeModule
  ],
  providers: [SubscriptionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
