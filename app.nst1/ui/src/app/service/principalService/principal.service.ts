import { Injectable } from '@angular/core';
import { Admin } from 'src/app/model/admin';

@Injectable({
  providedIn: 'root',
})
export class PrincipalService {
  _loggedAdmin: Admin;

  constructor() {}

  public get loggedAdmin() {
    return this._loggedAdmin;
  }

  public set loggedAdmin(admin: Admin) {
    this._loggedAdmin = admin;
  }
}
