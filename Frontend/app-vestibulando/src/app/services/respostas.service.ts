import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IResposta from '../interfaces/IResposta';

@Injectable({
  providedIn: 'root'
})
export class RespostasService {
  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  consultarComoAdmin(){
    return this.http.get<[IResposta]>(`${this.baseUrl}/respostas/admin`)
  }



  /* constructor(private http: HttpClient) { }

  consultarComoAdmin(){
    return this.http.get<[IResposta]>("http://localhost:4200/resposta/admin")
  } */
}
