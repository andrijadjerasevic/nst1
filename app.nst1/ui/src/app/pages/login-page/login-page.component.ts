import { Component, OnInit } from '@angular/core';
import { Admin } from 'src/app/models/admin.model';
import { AdminApiService } from 'src/app/services/admin-api.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  admin: Admin = {};

  constructor(private adminApiService: AdminApiService) {}

  ngOnInit(): void {}

  login(): void {
    console.log('Admin -> ', this.admin);
    this.adminApiService.login(this.admin).subscribe(
      (response) => {
        console.log('response -> ', response);
      },
      (error) => {
        console.error('ERROR ->', error);
      }
    );
  }

  reset(): void {
    this.admin = {};
  }
}
