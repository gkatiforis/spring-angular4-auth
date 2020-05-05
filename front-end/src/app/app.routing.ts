import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {SearchComponent} from "./components/search/search.component";
import {UrlPermission} from "./urlPermission/url.permission";


const appRoutes: Routes = [
  { path: 'search', component: SearchComponent ,canActivate: [UrlPermission] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: '**', redirectTo: '/login' }
];

export const routing = RouterModule.forRoot(appRoutes);
