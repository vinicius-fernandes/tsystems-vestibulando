import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IPage from '../interfaces/IPage';
import IRankingSimulado from "../interfaces/IRankingSimulado";

@Injectable({
  providedIn: 'root'
})
export class RankingSimuladoService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl:String) { }

  consultar(id:number){
    return this.http.get<[IRankingSimulado]>(`${this.baseUrl}/respostasUsuarios/rankingSimulado/${id}`);
  }

  rankingGeral(params:any){
    return this.http.get<IPage>(`${this.baseUrl}/respostasUsuarios/rankingGlobal`,{params})
  }

}
