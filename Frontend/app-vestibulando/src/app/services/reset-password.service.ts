import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import IAlterarSenha from '../interfaces/IAlterarSenha';
import IGerarTokenResetPasswordDTO from '../interfaces/IGerarTokenResetPasswordDTO';

@Injectable({
  providedIn: 'root'
})
export class ResetPasswordService {

  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl:String) { }


  gerarToken(data:IGerarTokenResetPasswordDTO){
    return this.http.post<string>(`${this.baseUrl}/passwordReset/gerarToken`,data)

  }
  alterarSenha(data:IAlterarSenha){
    return this.http.post<string>(`${this.baseUrl}/passwordReset/alterarSenha`,data)

  }

}
