import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { Observable } from "rxjs";
import { AuthService } from "../services/auth.service";
import JwtTokenService from "../services/jwt-token.service";

@Injectable({
  providedIn: 'root'
})
export class ValidUserGuard implements CanActivate, CanActivateChild {
  constructor(private router: Router, private jwtTokenService: JwtTokenService, private authService: AuthService, private toastr: ToastrService) { }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    return this.canActivate(childRoute, state)
  }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let user = this.jwtTokenService.getTokenDecoded()

    if (user == null && !this.jwtTokenService.getIsRefreshing()) {
      this.toastr.error('Você precisa realizar o login para acessar o recurso', 'Login exigido')
      this.authService.redirectUrl = state.url
      this.router.navigate(['login'])
    }
    return true
  }

}
