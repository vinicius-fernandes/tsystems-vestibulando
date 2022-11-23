import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SimuladoService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string) {



   }
}
