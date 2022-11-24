import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IPergunta from '../interfaces/IPergunta';

@Injectable({
  providedIn: 'root'
})
export class QuestoesService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  listarTodas(){
    return this.http.get<[IPergunta]>(`${this.baseUrl}/perguntas/todas`);
  }

}
