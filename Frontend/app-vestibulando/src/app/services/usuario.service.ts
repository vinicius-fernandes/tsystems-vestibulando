import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IPage from '../interfaces/IPage';
import IUsuario from '../interfaces/IUsuario';
import IUsuarioDTO from '../interfaces/IUsuarioDTO';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: String) { }

  consultar(){
    return this.http.get<[IUsuario]>(`${this.baseUrl}/usuarios`);
  }

  consultarPaginado(params:any){
    return this.http.get<IPage>(`${this.baseUrl}/usuarios/paginado`,{params});
  }

  excluir(idUsuario: any){
    return this.http.delete(`${this.baseUrl}/usuarios/${idUsuario}`);

  }
  consultarbyId(idUsuario: any) {
    return this.http.get<IUsuario>(`${this.baseUrl}/usuarios/${idUsuario}`);
  }

  alterar(usuario: IUsuario){
    return this.http.put<IUsuario>(`${this.baseUrl}/usuarios/${usuario.id}`,usuario)
  }

  cadastrar(usuario: IUsuarioDTO|IUsuario){
    return this.http.post<IUsuario>(`${this.baseUrl}/usuarios`, usuario)
  }

  pesquisar(idRole: number, texto: String,params:any){
    return this.http.get<IPage>(`${this.baseUrl}/usuarios/pesquisar/${idRole}?pesquisa=${texto}`,{params})
  }
}


