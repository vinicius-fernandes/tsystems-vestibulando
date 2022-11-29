import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import JwtTokenService from "../services/jwt-token.service";

@Injectable({
  providedIn: 'root'
})
export class AdmGuard implements CanActivate
{
  constructor(private router: Router,private jwtTokenService: JwtTokenService) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let user = this.jwtTokenService.getTokenDecoded()

    if(user == null && !this.jwtTokenService.getIsRefreshing()){
      this.router.navigate(['login'])
    }
    //let tokenDecoded = JSON.stringify(jwt(token))
    //let user = JSON.parse(tokenDecoded)


   // if(user.authorities.indexOf("ROLE_ADMIN") == -1){
    if(!this.jwtTokenService.checkAuthoritie("ROLE_ADMIN")){
      this.router.navigate(['app/info/Precisa ser um administador para acessar o recurso'])
    }
    return true
  }

}
