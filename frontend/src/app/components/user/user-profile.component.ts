import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user/User";
import { UserService } from "src/app/services/user.service";

@Component({
  templateUrl: 'user-profile.component.html'
})
export class UserProfileComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.profile().subscribe({
      next: user => this.user = user,
      error: err => console.log('Error: ', err)
    });
  }

}