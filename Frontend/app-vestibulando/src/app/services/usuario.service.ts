import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IUsuario from '../interfaces/IUsuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl:String) { }

  consultar(){
    return this.http.get<[IUsuario]>(`${this.baseUrl}/usuarios`);   
  }

  excluir(idUsuario: any){
    return this.http.delete(`${this.baseUrl}/usuarios/${idUsuario}`); 

  }
  consultarbyId(idUsuario: any){
    return this.http.get<IUsuario>(`${this.baseUrl}/usuarios/${idUsuario}`);
  }
  
  alterar(usuario: IUsuario){
    console.log(usuario)
    return this.http.put<IUsuario>(`${this.baseUrl}/usuarios/${usuario.id}`,usuario)

  }
}


