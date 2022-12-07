import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IMateria from '../interfaces/IMateria';
import IPage from '../interfaces/IPage';

@Injectable({
  providedIn: 'root'
})
export class MateriasService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  consultar() {
    return this.http.get<[IMateria]>(`${this.baseUrl}/materia`);
  }
  consultarPaginado(params:any){
    return this.http.get<IPage>(`${this.baseUrl}/materia/paginado`,{params});
  }

  consultarPorId(id: number) {
    return this.http.get<IMateria>(`${this.baseUrl}/materia/${id}`)
  }

  excluir(id: number) {
    return this.http.delete(`${this.baseUrl}/materia/${id}`, { responseType: "arraybuffer" })
  }

  salvar(dados: IMateria) {
    return this.http.post<IMateria>(`${this.baseUrl}/materia`, dados)
  }

  editar(dados: IMateria) {
    return this.http.put<IMateria>(`${this.baseUrl}/materia/${dados.id}`, dados)
  }
}
