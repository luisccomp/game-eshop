import { Component, OnInit } from "@angular/core";
import { UserLogin } from "./UserLogin";

@Component({
  selector: 'app-gamestore-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: UserLogin;

  ngOnInit(): void {
    this.userLogin = {
      email: null,
      password: null
    }
  }

  submit(): void {
    console.log(this.userLogin);
  }

}