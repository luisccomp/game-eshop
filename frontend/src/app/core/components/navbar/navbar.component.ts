import { Component } from "@angular/core";

@Component({
  selector: 'app-gamestore-navbar',
  templateUrl: 'navbar.component.html'
})
export class NavbarComponent {

  public isMenuCollapsed: boolean = true;

  collapse() {
    this.isMenuCollapsed = !this.isMenuCollapsed;
  }

}