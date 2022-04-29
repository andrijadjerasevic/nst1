import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/shared/models/admin.model';
import { AdminApiService } from 'src/app/shared/services/admin-api.service';
import { PrincipalService } from 'src/app/shared/services/principal.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  admin: Admin;
  title = 'NST1 UI Application';
  errorMessage: string;

  constructor(
    private adminApiService: AdminApiService,
    private router: Router,
    private principal: PrincipalService
  ) {
    this.admin = {};
    this.errorMessage = '';
  }

  ngOnInit(): void {}

  login(): void {
    console.log('Admin -> ', this.admin);
    this.adminApiService.login(this.admin).subscribe(
      (response) => {
        console.log('response -> ', response);
        this.principal.loggedAdmin = response;
        this.router.navigate(['/home']);
      },
      (error) => {
        this.errorMessage = error.message;
        console.error('error ->', error);
      }
    );
  }

  reset(): void {
    this.admin = {};
  }
}
