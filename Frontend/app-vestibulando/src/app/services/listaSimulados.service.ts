import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import ISimulado from "../interfaces/ISimulado";
import * as http from "http";

@Injectable({
  providedIn: "root"
})
export class ListaSimuladosService{

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  listar() {
     return this.http.get<ISimulado[]>(`${this.baseUrl}/simulados`)
  }

}
