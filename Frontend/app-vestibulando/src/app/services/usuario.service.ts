import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IUsuario from '../interfaces/IUsuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl:String) { }

  consultar(){
    return this.http.get<[IUsuario]>(`${this.baseUrl}/usuario`);
  }
}
