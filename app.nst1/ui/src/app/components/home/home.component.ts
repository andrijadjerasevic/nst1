import { Component, OnInit } from '@angular/core';
import { Admin } from 'src/app/model/admin';
import { PrincipalService } from 'src/app/service/principalService/principal.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  loggedAdmin: Admin;
  constructor(private principal: PrincipalService) {}

  ngOnInit(): void {
    this.loggedAdmin = this.principal.loggedAdmin;
  }
}
