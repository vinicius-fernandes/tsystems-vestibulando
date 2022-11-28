import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import  JwtTokenService  from './jwt-token.service';

@Injectable({
  providedIn: 'root'
})



export class AuthService {
  HTTP_OPTIONS = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      
      Authorization: 'Basic ' + btoa(this.auth_client + ':' + this.auth_secret)
    })
  };
  redirectUrl = '';


  constructor( @Inject('BASE_API_URL') private baseUrl:String,
               @Inject('OAUTH_CLIENT') private auth_client:String,
              @Inject('OAUTH_SECRET') private auth_secret:String,
              private http: HttpClient,
              private tokenService: JwtTokenService)
              {

              }

    login(loginData: any): Observable<any> {
      this.tokenService.removeToken();
      this.tokenService.removeRefreshToken();
      const body = new HttpParams()
        .set('username', loginData.username)
        .set('password', loginData.password)
        .set('grant_type', 'password');
        return this.http.post<any>(this.baseUrl + '/oauth/token', body, this.HTTP_OPTIONS)

    }

    logout(): void {
      this.tokenService.removeToken();
      this.tokenService.removeRefreshToken();
    }


    refreshToken(refreshData: any): Observable<any> {
      this.tokenService.removeToken();
      this.tokenService.removeRefreshToken();
      const body = new HttpParams()
        .set('refresh_token', refreshData.refresh_token)
        .set('grant_type', 'refresh_token');
      return this.http.post<any>(this.baseUrl + '/oauth/token', body, this.HTTP_OPTIONS)

    }


}
