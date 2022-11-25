import { Injectable } from '@angular/core';
const ACCESS_TOKEN:string = 'access_token';
const REFRESH_TOKEN:string = 'refresh_token';
@Injectable({
  providedIn: 'root'
})
export default class JwtTokenService {

  constructor() { }

  getToken(): string | null{
    return localStorage.getItem(ACCESS_TOKEN);
  }

  getRefreshToken(): string | null{
    return localStorage.getItem(REFRESH_TOKEN);
  }

  saveToken(token: string ): void {
    localStorage.setItem(ACCESS_TOKEN, token);
  }

  saveRefreshToken(refreshToken: string): void {
    localStorage.setItem(REFRESH_TOKEN, refreshToken);
  }

  removeToken(): void {
    localStorage.removeItem(ACCESS_TOKEN);
  }

  removeRefreshToken(): void {
    localStorage.removeItem(REFRESH_TOKEN);
  }

}
