import { Component, Inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import IUsuarioDTO from 'src/app/interfaces/IUsuarioDTO';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import JwtTokenService from 'src/app/services/jwt-token.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit{

  form: FormGroup
  constructor(private http: HttpClient, @Inject('BASE_API_URL') private baseUrl: string, private formBuilder: FormBuilder, private service: UsuarioService, private router: Router, private toastr: ToastrService,private jwtTokenService:JwtTokenService) {
    this.form = this.formBuilder.group(
      {
        nome: new FormControl("", [Validators.minLength(5), Validators.required]),
        email: new FormControl("", [Validators.email, Validators.required]),
        senha: new FormControl("", [Validators.minLength(6), Validators.maxLength(20), Validators.required])
      }
    )
  }

  ngOnInit(): void {
    if(this.jwtTokenService.getToken()!=null){
      this.router.navigate(['app','home'])
    }
  }

  cadastrar() {
    let dados: IUsuarioDTO = { email: this.form.value.email, nome: this.form.value.nome, senha: this.form.value.senha }
    this.service.cadastrar(dados).subscribe({
      next: () => { this.router.navigate(["login"]) },
      error: erro => {
        console.log(erro)
        this.toastr.error(erro.error.message, "Erro")
      }
    })
  }

}
