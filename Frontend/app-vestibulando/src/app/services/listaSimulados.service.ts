import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import ISimulado from "../interfaces/ISimulado";

@Injectable({
  providedIn: "root"
})
export class ListaSimuladosService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  listar() {
    return this.http.get<ISimulado[]>(`${this.baseUrl}/simulados`)
  }

  excluir(id: number) {
    return this.http.delete(`${this.baseUrl}/simulados/${id}`, { responseType: 'arraybuffer' })
  }

  converterData(timestamp: number): string {
    let date = new Date(timestamp * 1000)

    return date.toLocaleDateString('pt-BR')
      + ' às ' + date.toLocaleTimeString('pt-BR')
  }
}
