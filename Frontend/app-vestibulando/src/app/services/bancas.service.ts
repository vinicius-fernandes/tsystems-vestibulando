import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IBanca from '../interfaces/IBanca';

@Injectable({
  providedIn: 'root'
})
export class BancasService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  consultar() {
    return this.http.get<[IBanca]>(`${this.baseUrl}/banca`);
  }

  consultarPorId(id: number) {
    return this.http.get<IBanca>(`${this.baseUrl}/banca/${id}`);
  }

  excluir(id: number) {
    return this.http.delete(`${this.baseUrl}/banca/${id}`, { responseType: 'arraybuffer' });
  }

  salvar(dados: IBanca) {
    return this.http.post<IBanca>(`${this.baseUrl}/banca`, dados)
  }

  editar(dados: IBanca) {
    return this.http.put<IBanca>(`${this.baseUrl}/banca/${dados.id}`, dados)
  }
}
