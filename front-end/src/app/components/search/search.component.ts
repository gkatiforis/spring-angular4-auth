import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/model.user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {
  currentUser: User;
  user:User;
  constructor(public userService: UserService, public router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.user=new User();
  }

  ngOnInit() {
  }

  findUser() {

    this.userService.getUser(this.user).subscribe(
      data => {

       // alert(data);
       this.user = data.json();
      },
  error => {
    this.user=new User();
    this.user.id = '';
  });

  }
// login out from the app
  logOut() {
    this.userService.logOut()
      .subscribe(
        data => {
          this.router.navigate(['/login']);
        },
        error => {

        });
  }
}
