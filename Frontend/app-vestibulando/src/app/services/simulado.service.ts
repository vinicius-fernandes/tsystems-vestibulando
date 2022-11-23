import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IGerarSimulado from '../interfaces/IGerarSimulado';

@Injectable({
  providedIn: 'root'
})
export class SimuladoService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) {



   }


   gerar(simulado:IGerarSimulado){
    return this.http.post(`${this.baseUrl}/simulados/gerar`,simulado)
   }

}
