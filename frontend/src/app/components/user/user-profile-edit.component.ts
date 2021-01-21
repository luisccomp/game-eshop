import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "src/app/models/user/User";
import { UserUpdate } from "src/app/models/user/UserUpdate";
import { UserService } from "src/app/services/user.service";

@Component({
  templateUrl: 'user-profile-edit.component.html'
})
export class UserProfileEditComponent implements OnInit {

  user: User;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.userService.profile().subscribe({
      next: user => this.user = user,
      error: err => console.log('Error: ', err)
    });
  }

  updateProfile(): void {
    let userUpdateRequest: UserUpdate = {
      first_name: this.user.first_name,
      last_name: this.user.last_name,
      description: this.user.description
    };

    this.userService.update(this.user.id, userUpdateRequest).subscribe({
      next: user => {
        this.user = user;
        this.router.navigate(['/profile'])
      },
      error: err => {
        console.log(err);
      }
    });
  }

}