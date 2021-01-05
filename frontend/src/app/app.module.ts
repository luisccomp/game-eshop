import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from '../core/components/navbar/navbar.component';
import { LoginButtonComponent } from '../core/components/login/login-button.component';
import { RouterModule } from '@angular/router';
import { LoginComponent } from '../core/components/login/login.component';
import { FormsModule } from '@angular/forms';
import { CoreModule } from 'src/core/core.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    RouterModule.forRoot([
      {
        path: 'login',
        component: LoginComponent
      }
    ]),
    FormsModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
