import { Component } from '@angular/core';
import { Admin } from './model/admin';
import { PrincipalService } from './service/principalService/principal.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  constructor(private principal: PrincipalService) {}
  ngOnInit(): void {}

  public get loggedAdmin(): Admin {
    return this.principal.loggedAdmin;
  }
}
