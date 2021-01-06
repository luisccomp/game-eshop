import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from 'src/app/models/user/UserLogin';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-gamestore-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  userLogin: UserLogin;
  loginFailed: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) {}

  ngOnInit(): void {
    if (!this.authenticationService.isLoggedIn()) {
      this.userLogin = {
        email: null,
        password: null,
      };
    } else {
      this.router.navigate(['/']);
    }
  }

  submit(): void {
    // console.log(this.userLogin);
    this.authenticationService.login(this.userLogin)
        .subscribe({
          next: userToken => {
            localStorage.setItem('token', userToken.token);
            this.router.navigate(['/']);
          },
          error: err => {
            console.log('Error: ', err);
            this.loginFailed = true;
          }
        });
  }

}
