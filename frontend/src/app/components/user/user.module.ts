import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { AuthenticationGuard } from "src/app/guards/authentication.guard";
import { UserProfileEditComponent } from "./user-profile-edit.component";
import { UserProfileComponent } from "./user-profile.component";

@NgModule({
  declarations: [
    UserProfileComponent,
    UserProfileEditComponent,
  ],
  imports: [
    RouterModule.forChild([
      {
        path: 'profile',
        component: UserProfileComponent,
        canActivate: [AuthenticationGuard]
      },
      {
        path: 'profile/edit',
        component: UserProfileEditComponent,
        canActivate: [AuthenticationGuard]
      }
    ]),
    CommonModule,
    FormsModule
  ],
  exports: []
})
export class UserModule {}