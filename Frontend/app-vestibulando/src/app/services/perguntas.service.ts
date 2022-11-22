import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import IMateria from '../interfaces/IMateria';

@Injectable({
  providedIn: 'root'
})
export class PerguntasService {

  constructor(private http:HttpClient) { }

  consultar(){
    return this.http.get<[IMateria]>("http://localhost:8080/materia");
  }
}
