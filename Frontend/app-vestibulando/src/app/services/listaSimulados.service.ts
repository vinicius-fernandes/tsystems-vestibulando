import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import IPage from "../interfaces/IPage";
import IPesquisaSimuladoDTO from "../interfaces/IPesquisaSimuladoDTO";
import ISimulado from "../interfaces/ISimulado";

@Injectable({
  providedIn: "root"
})
export class ListaSimuladosService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  listar() {
    return this.http.get<ISimulado[]>(`${this.baseUrl}/simulados`)
  }

  listarPaginado(params:any){
    return this.http.get<IPage>(`${this.baseUrl}/simulados/paginado`,{params})
  }

  pesquisar(params:any,pesquisa:IPesquisaSimuladoDTO){
    return this.http.post<IPage>(`${this.baseUrl}/simulados/pesquisar`,pesquisa,{params})

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
