import { Injectable } from '@angular/core';
import { Admin } from '../models/admin.model';

@Injectable({
  providedIn: 'root',
})
export class PrincipalService {
  _loggedAdmin: Admin;

  constructor() {}

  public get loggedUser(): Admin {
    return this._loggedAdmin;
  }

  public set loggedAdmin(admin: Admin) {
    this._loggedAdmin = admin;
  }
}
