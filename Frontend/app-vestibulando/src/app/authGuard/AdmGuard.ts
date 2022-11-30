import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import JwtTokenService from "../services/jwt-token.service";

@Injectable({
  providedIn: 'root'
})
export class AdmGuard implements CanActivate
{
  constructor(private router: Router,private jwtTokenService: JwtTokenService, private toastr: ToastrService) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let user = this.jwtTokenService.getTokenDecoded()

    if(user == null && !this.jwtTokenService.getIsRefreshing()){
      this.router.navigate(['login'])
    }
    //let tokenDecoded = JSON.stringify(jwt(token))
    //let user = JSON.parse(tokenDecoded)


   // if(user.authorities.indexOf("ROLE_ADMIN") == -1){
    if(!this.jwtTokenService.checkAuthoritie("ROLE_ADMIN")){
      this.toastr.error('Você precisa ser um administrador para acessar o recurso', 'Não autorizado')
      this.router.navigate(['app/home'])
    }
    return true
  }

}
