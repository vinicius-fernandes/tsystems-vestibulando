import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IGerarTokenResetPasswordDTO from 'src/app/interfaces/IGerarTokenResetPasswordDTO';
import { ResetPasswordService } from 'src/app/services/reset-password.service';

@Component({
  selector: 'app-esqueceu-senha',
  templateUrl: './esqueceu-senha.component.html',
  styleUrls: ['./esqueceu-senha.component.css']
})
export class EsqueceuSenhaComponent {
  form: FormGroup

  loading:boolean = false;
  constructor(private _router: Router, private formBuilder: FormBuilder,private toastr: ToastrService,private resetPassService:ResetPasswordService,private route: ActivatedRoute){
    this.form = this.formBuilder.group( {
      username: new FormControl("", [Validators.email, Validators.required]),
    })
  }

  resetPass(){

    let dados : IGerarTokenResetPasswordDTO = {email:this.form.controls['username'].value,urlFrom:location.origin}
    this.loading=true;
    this.resetPassService.gerarToken(dados).subscribe({
      next:(msg)=>{
        this.toastr.success("Se houver um usuário cadastrado com o e-mail em breve um link para o reset de senha será enviado","Sucesso")
        this._router.navigate([''])
      },
      error:(erro)=>{
        console.log(erro)
        this.toastr.error("Ops ocorreu um erro ao pedir o reset de senha","Erro")
      },
      complete:()=>{
        this.loading=false;
      }
    })
  }
}
