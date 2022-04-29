import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { PrincipalService } from '../services/principal.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private principal: PrincipalService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const adminAuthentificated = this.principal.loggedAdmin !== null;
    if (adminAuthentificated) {
      return this.router.parseUrl('/login');
    }
    return adminAuthentificated;
  }

  canLoad(
    route: import('@angular/router').Route,
    segments: import('@angular/router').UrlSegment[]
  ): boolean | Observable<boolean> | Promise<boolean> {
    return this.isAdminAuthentificated();
  }

  isAdminAuthentificated() {
    return this.principal.loggedAdmin !== null;
  }
}
