import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import INotasSimuladosUsuario from '../interfaces/INotasSimuladosUsuario';
import IRespostaUsuario from '../interfaces/IRespostaUsuario';
import IResultadoSimuladoUser from '../interfaces/IResultadoSimuladoUser';

@Injectable({
  providedIn: 'root'
})
export class RespostasUsuariosService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) {

  }

  salvar(respUser: IRespostaUsuario) {
    return this.http.post<IRespostaUsuario>(`${this.baseUrl}/respostasUsuarios`, respUser)
  }

  resultado(idSimulado: number, idUser: number) {
    return this.http.get<IResultadoSimuladoUser>(`${this.baseUrl}/respostasUsuarios/notaSimuladoUsuario/${idUser}/${idSimulado}`)
  }

  notasSimuladosUsuario(idUser: number) {
    return this.http.get<[INotasSimuladosUsuario]>(`${this.baseUrl}/respostasUsuarios/notasSimuladosUsuario/${idUser}`)
  }
  perguntasCorretaSimuladoUsuario(idUser: number, idSimulado: number) {
    return this.http.get<[number]>(`${this.baseUrl}/respostasUsuarios/perguntasCorretas/${idUser}/${idSimulado}`)
  }
}
