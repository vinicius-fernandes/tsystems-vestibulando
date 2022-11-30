import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import JwtTokenService from 'src/app/services/jwt-token.service';

@Component({
  selector: 'app-tela',
  templateUrl: './tela.component.html',
  styleUrls: ['./tela.component.css']
})
export class TelaComponent implements OnInit {

  constructor(private jwtTokenService: JwtTokenService, private _router: Router) {

  }
  ngOnInit(): void {
    if (this.jwtTokenService.getToken() != null) {
      this._router.navigate(['app', 'home'])
    }
  }
}
