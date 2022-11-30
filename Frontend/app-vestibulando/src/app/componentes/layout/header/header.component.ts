import { Component } from '@angular/core';
import { Router } from '@angular/router';
import JwtTokenService from 'src/app/services/jwt-token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  logado: boolean = false
  constructor(private tokenService: JwtTokenService, private router: Router) {
    this.logado = this.tokenService.getToken() != null
  }

  logout() {
    this.tokenService.removeToken();
    this.tokenService.removeRefreshToken();
    this.router.navigateByUrl("/login");
  }
}
