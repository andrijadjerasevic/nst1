import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  title = 'NST1';
  errorMessage = '';

  admin: Admin;
  constructor(
    private adminService: AdminServiceService,
    private router: Router
  ) {
    this.admin = {};
  }

  ngOnInit(): void {}

  login(): void {
    this.adminService.login(this.admin).subscribe(
      (response) => {
        if (response) {
          this.admin = response;
          this.router.navigate(['home']);
        }
      },
      (error) => {
        this.errorMessage = 'Wrong email or password';
        console.error('ERROR -> ', error);
      }
    );
  }

  resetFields() {
    this.admin = {};
  }
}
