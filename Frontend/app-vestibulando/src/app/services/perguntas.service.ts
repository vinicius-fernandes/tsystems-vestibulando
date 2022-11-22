import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IMateria from '../interfaces/IMateria';

@Injectable({
  providedIn: 'root'
})
export class PerguntasService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  consultar(){
    return this.http.get<[IMateria]>(`${this.baseUrl}/materia`);
  }
}
