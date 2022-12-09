import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IRole from '../interfaces/IRole'

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: String) { }

  consultar() {
    return this.http.get<[IRole]>(`${this.baseUrl}/roles`);
  }
}
