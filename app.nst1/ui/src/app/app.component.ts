import { Component } from '@angular/core';
import { PrincipalService } from './shared/services/principal.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'NST1';

  constructor(private principal: PrincipalService) {}
  ngOnInit(): void {}

  get loggedAdmin() {
    return this.principal.loggedAdmin;
  }
}
