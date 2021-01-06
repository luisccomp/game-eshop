import { Component, Input } from "@angular/core";

@Component({
  selector: 'app-gamestore-alert',
  templateUrl: 'alert.component.html'
})
export class AlertComponent {

  @Input()
  dismissible: boolean = false;

  @Input()
  type: string = "primary"

  isClosed: boolean = false;

  close() {
    this.isClosed = true;
  }

}