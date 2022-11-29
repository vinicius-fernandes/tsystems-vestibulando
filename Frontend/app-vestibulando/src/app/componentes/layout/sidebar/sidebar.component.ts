import { Component, OnInit } from '@angular/core';
import JwtTokenService from 'src/app/services/jwt-token.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit{

  isAdmin:boolean=false;
  constructor(private jwtService:JwtTokenService){

  }
  ngOnInit(): void {
    this.isAdmin = this.jwtService.checkAuthoritie('ROLE_ADMIN')
  }
}
