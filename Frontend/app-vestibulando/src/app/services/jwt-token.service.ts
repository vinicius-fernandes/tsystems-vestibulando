import { Injectable } from '@angular/core';
import ITokenDecoded from '../interfaces/ITokenDecoded';
import jwt from 'jwt-decode';
const ACCESS_TOKEN: string = 'access_token';
const REFRESH_TOKEN: string = 'refresh_token';
@Injectable({
  providedIn: 'root'
})
export default class JwtTokenService {

  isRefreshing: boolean = false;
  constructor() { }

  setIsRefreshing(isRefreshing: boolean) {
    this.isRefreshing = isRefreshing;
  }

  getIsRefreshing(): boolean {
    return this.isRefreshing;
  }

  getToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN);
  }

  getRefreshToken(): string | null {
    return localStorage.getItem(REFRESH_TOKEN);
  }

  saveToken(token: string): void {
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

  getTokenDecoded(): ITokenDecoded | null {
    let token = this.getToken()
    if (token != null) {
      return jwt(token)
    }
    return token
  }

  checkAuthoritie(authoritie: string): boolean {
    let user = this.getTokenDecoded()
    if (user == null) {
      return false
    }
    if (user?.authorities.includes(authoritie)) {
      return true
    }

    return false
  }
}
