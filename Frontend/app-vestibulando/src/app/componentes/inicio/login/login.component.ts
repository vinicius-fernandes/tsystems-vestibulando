import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from "@angular/router";
import { AuthService } from 'src/app/services/auth.service';
import JwtTokenService from 'src/app/services/jwt-token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  form: FormGroup

  constructor(private _router: Router, private formBuilder: FormBuilder,private authService:AuthService,private jwtTokenService:JwtTokenService) {
    this.form = this.formBuilder.group(
      {
        username: new FormControl("", [Validators.email, Validators.required]),
        password: new FormControl("", [ Validators.required])
      }
    )
   }

  login(){
    this.authService.login(this.form.value)
    .subscribe({
      next:(value) =>{
        this.jwtTokenService.saveToken(value.access_token)
        this.jwtTokenService.saveRefreshToken(value.refresh_token)
        console.log(value.access_token)
        this.redirecionarGerarSimulado()
      },
      error:(erro)=>{
        console.log(erro)
      }
    });

  }
  redirecionarCadastro() {
    this._router.navigateByUrl('/cadastro')
  }

  redirecionarGerarSimulado() {
    this._router.navigate(['app','home'])
  }
}
