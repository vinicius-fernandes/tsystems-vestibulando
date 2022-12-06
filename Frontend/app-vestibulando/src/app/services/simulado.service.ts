import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IGerarSimulado from '../interfaces/IGerarSimulado';
import ISimuladoDTO from '../interfaces/ISimuladoDTO';

@Injectable({
  providedIn: 'root'
})
export class SimuladoService {

  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  gerar(simulado: IGerarSimulado) {
    return this.http.post<ISimuladoDTO>(`${this.baseUrl}/simulados/gerar`, simulado)
  }

  realizar(id: number) {
    return this.http.get<ISimuladoDTO>(`${this.baseUrl}/simulados/realizar/${id}`)
  }

}
