import { Admin } from './admin';

export class Employee {
  constructor(
    public employeeEmail: string,
    public firstName: string,
    public lastName: string,
    public admin: Admin
  ) {}
}
