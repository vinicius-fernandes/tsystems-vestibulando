import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import JwtTokenService from 'src/app/services/jwt-token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  form: FormGroup

  public mostrarSenha: boolean = false

  constructor(private _router: Router, private formBuilder: FormBuilder,private authService:AuthService,private jwtTokenService:JwtTokenService,private toastr: ToastrService) {
    this.form = this.formBuilder.group(
      {
        username: new FormControl("", [Validators.email, Validators.required]),
        password: new FormControl("", [ Validators.required])
      }
    )
   }
  ngOnInit(): void {
    if(this.jwtTokenService.getToken()!=null){
      this._router.navigate(['app','home'])
    }
  }

  login(){
    console.log(this.form.value)
    this.authService.login(this.form.value)
    .subscribe({
      next:(value) =>{
        this.jwtTokenService.saveToken(value.access_token)
        this.jwtTokenService.saveRefreshToken(value.refresh_token)
        console.log(value.access_token)
        console.log("refresh "+value.refresh_token)
        if(this.authService.redirectUrl==null){
        this.redirecionarGerarSimulado()
        }
        else{
          this._router.navigate([this.authService.redirectUrl])
        }
      },
      error:(erro)=>{
        console.log(erro)
        if(erro.status == 400){
          this.toastr.error("Usuário e/ou senha inválidos","Erro")
        }
        else{
          this.toastr.error("Ocorreu um erro ao efetuar o login","Erro")
        }
      }
    });
    

  }
  redirecionarCadastro() {
    this._router.navigateByUrl('/cadastro')
  }

  redirecionarGerarSimulado() {
    this._router.navigate(['app']);
    location. reload();
  }

  mudaVisibilidadeSenha() {
    this.mostrarSenha = !this.mostrarSenha
  }
}