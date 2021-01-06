import { Component } from "@angular/core";
import { AuthenticationService } from "src/app/services/authentication.service";

@Component({
  selector: 'app-gamestore-login-button',
  templateUrl: './login-button.component.html'
})
export class LoginButtonComponent {

  constructor(private authService: AuthenticationService) {}

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
  }

}