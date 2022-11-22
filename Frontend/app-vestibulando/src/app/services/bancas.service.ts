import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IBanca from '../interfaces/IBanca';

@Injectable({
  providedIn: 'root'
})
export class BancasService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) { }

  consultar(){
    return this.http.get<[IBanca]>(`${this.baseUrl}/banca`);
  }
}
