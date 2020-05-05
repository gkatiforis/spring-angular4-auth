import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {User} from "../../model/model.user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  user: User=new User();
  errorMessage:string;
  constructor(private userService :UserService, private router: Router) { }



  ngOnInit() {
  }

  login(){
    this.userService.logIn(this.user)
      .subscribe(data=>{
        this.router.navigate(['/search']);
        },err=>{
        this.errorMessage="Password is incorrect";
        }
      )
  }


}
