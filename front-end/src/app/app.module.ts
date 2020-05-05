import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule } from '@angular/forms';
import { UserService } from "./services/user.service";
import {HttpModule} from "@angular/http";
import { SearchComponent } from './components/search/search.component';
import {routing} from "./app.routing";

import {UrlPermission} from "./urlPermission/url.permission";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SearchComponent,


  ],
  imports: [
    BrowserModule,HttpModule,FormsModule,routing,
  ],
  providers: [UserService,UrlPermission],
  bootstrap: [AppComponent]
})
export class AppModule { }
