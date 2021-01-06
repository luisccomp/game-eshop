import { NgModule } from "@angular/core";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { AlertComponent } from "./components/alert/alert.component";

@NgModule({
  declarations: [
    AlertComponent
  ],
  imports: [
    NgbModule
  ],
  exports: [
    AlertComponent
  ]
})
export class SharedModule {}