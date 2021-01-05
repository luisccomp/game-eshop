import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { LoginButtonComponent } from "./components/login/login-button.component";
import { LoginComponent } from "./components/login/login.component";
import { NavbarComponent } from "./components/navbar/navbar.component";

@NgModule({
  declarations: [
    NavbarComponent,
    LoginButtonComponent,
    LoginComponent
  ],
  imports: [
    RouterModule.forChild([
      {
        path: 'login',
        component: LoginComponent
      }
    ]),
    FormsModule,
    CommonModule,
    NgbModule
  ],
  exports: [
    NavbarComponent
  ]
})
export class CoreModule {}