import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IResposta from '../interfaces/IResposta';

@Injectable({
  providedIn: 'root',
})
export class RespostasService {
  constructor(
    private http: HttpClient,
    @Inject('BASE_API_URL') private baseUrl: string
  ) {}

  salvar(dados:IResposta){
    
    return this.http.post<IResposta>(`${this.baseUrl}/respostas`, dados)
  }

  consultarComoAdmin() {
    return this.http.get<[IResposta]>(`${this.baseUrl}/respostas/admin`);
  }

  
  
}
