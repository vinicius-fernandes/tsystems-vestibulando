import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IPergunta from '../interfaces/IPergunta';

@Injectable({
  providedIn: 'root',
})
export class QuestoesService {
  constructor(
    private http: HttpClient,
    @Inject('BASE_API_URL') private baseUrl: string
  ) {}

  salvar(dados: IPergunta) {
    return this.http.post<IPergunta>(`${this.baseUrl}/perguntas`, dados)
  }

  consultar() {
    return this.http.get<[IPergunta]>(`${this.baseUrl}/perguntas/todas`);
  }

  consultarPorId(id: number) {
    return this.http.get<IPergunta>(`${this.baseUrl}/perguntas/${id}`);
  }

  editar(dados: IPergunta) {
    return this.http.put<IPergunta>(`${this.baseUrl}/perguntas/${dados.id}`, dados)
  }

  excluir(id: number) {
    return this.http.delete(`${this.baseUrl}/perguntas/${id}`, {responseType: 'arraybuffer'});
  }

}
