import { Component, OnInit } from '@angular/core';
import { catchError } from 'rxjs';
import { Admin } from 'src/app/model/admin';
import { AdminServiceService } from 'src/app/service/adminService/admin-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  title = 'NST1';

  admin: Admin;
  constructor(private adminService: AdminServiceService) {
    this.admin = {};
  }

  ngOnInit(): void {}

  login(): void {
    this.adminService.login(this.admin).subscribe(
      (response) => {
        this.admin = response;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  resetFields() {
    this.admin = {};
  }
}
