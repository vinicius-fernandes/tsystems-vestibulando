import { Component, Inject } from '@angular/core';
import IUsuario from 'src/app/interfaces/IUsuario';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import IUsuarioDTO from 'src/app/interfaces/IUsuarioDTO';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {

  form:FormGroup
  constructor(private http:HttpClient, @Inject('BASE_API_URL') private baseUrl: string, private formBuilder:FormBuilder, private service: UsuarioService, private router:Router) { 
    this.form = this.formBuilder.group(
      {
        nome: new FormControl("", [Validators.minLength(10), Validators.required]),
        email:new FormControl("", [Validators.email, Validators.required]),
        senha:new FormControl("",[Validators.minLength(6),Validators.min(1),Validators.maxLength(20),Validators.required])
      }
    )
   }

  cadastrar() {
    let dados:IUsuarioDTO = {email:this.form.value.email,nome:this.form.value.nome, senha:this.form.value.senha}
    this.service.cadastrar(dados).subscribe({
      next:(

      )=>{this.router.navigate(["login"])},
      error:( erro
        
      )=>{console.log(erro)
      }
    })
  }

}
