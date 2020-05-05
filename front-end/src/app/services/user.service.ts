import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions,Response} from '@angular/http';
import {User} from "../model/model.user";
import 'rxjs/add/operator/map';
import {AppComponent} from "../app.component";
import {HttpHeaders, HttpParams} from "@angular/common/http";
@Injectable()
export class UserService {
  constructor(public http: Http) { }

  public getUser( user: User) {

    let headers = new Headers();
    headers.append('Accept', 'application/json')

    headers.append("Authorization", "Basic " + localStorage.getItem('credential'));

    let options = new RequestOptions();
    options.headers=headers;

    return this.http.get(AppComponent.API_URL+'/user/' + user.id ,   options)
      .map(data => {
        if(data.status == 200)
             return data;
        else return null;
    });
  }

  public logIn(user: User){
    let headers = new Headers();
    headers.append('Accept', 'application/json')
    // creating base64 encoded String from username and password
    var base64Credential: string = btoa( user.username+ ':' + user.password);
    headers.append("Authorization", "Basic " + base64Credential);

    let options = new RequestOptions();
    options.headers=headers;

    return this.http.get(AppComponent.API_URL+"/user/login" ,   options)
      .map((response: Response) => {
      //alert(JSON.stringify(response.json()));
      let user = response.json().principal;// the returned user object is a principal object
      if (user) {
        localStorage.setItem('currentUser', JSON.stringify(user));
        localStorage.setItem('credential', base64Credential);
      }
    });
  }


  logOut() {
    return this.http.post(AppComponent.API_URL+"logout",{})
      .map((response: Response) => {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('credential');
      });

  }

  createAccount(user:User){
    return this.http.post(AppComponent.API_URL+'/user/register',user)
      .map(resp=>resp.json());
  }
}
