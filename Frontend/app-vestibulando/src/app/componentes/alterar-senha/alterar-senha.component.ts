import { FocusMonitor } from '@angular/cdk/a11y';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { TransitionCheckState } from '@angular/material/checkbox';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IAlterarSenha from 'src/app/interfaces/IAlterarSenha';
import { ResetPasswordService } from 'src/app/services/reset-password.service';

@Component({
  selector: 'app-alterar-senha',
  templateUrl: './alterar-senha.component.html',
  styleUrls: ['./alterar-senha.component.css']
})
export class AlterarSenhaComponent {
  form: FormGroup
  public mostrarSenha: boolean = false
  public mostrarConfirmaSenha: boolean=false
  constructor(private _router: Router, private formBuilder: FormBuilder,private toastr: ToastrService,private resetPassService:ResetPasswordService,private route: ActivatedRoute){
    this.form = this.formBuilder.group(
      {
        senha: new FormControl("", [ Validators.required, Validators.minLength(6), Validators.maxLength(20)]),
        confirmarSenha: new FormControl("", [ Validators.required, Validators.minLength(6), Validators.maxLength(20),this.validaSenha()])
      },

    )

  }

  mudaVisibilidadeSenha() {
    this.mostrarSenha = !this.mostrarSenha
  }
  mudaVisibilidadeConfirmaSenha() {
    this.mostrarConfirmaSenha = !this.mostrarConfirmaSenha
  }
  validaSenha() {
    const validator: ValidatorFn = (control: AbstractControl) => {
        if(!control.parent)
          return null

          const senha = control.parent.get('senha');
          const confirmarSenha = control.parent.get('confirmarSenha');
          console.log(senha)

          if (!senha || !confirmarSenha){
              return null;
          }

          if (confirmarSenha.value === ''){
              return null;
          }

          if (senha.value === confirmarSenha.value){
              return null;
          }
          console.log('diferentets')

          return { 'senhas_diferentes': true };

    };

    return validator;
  }

  alterarSenha(){
    const routeParams = this.route.snapshot.paramMap

    let token = routeParams.get('token') || ''

    console.log(token)

    let dados : IAlterarSenha = {novaSenha:this.form.controls['senha'].value,token:token,confirmarNovaSenha:this.form.controls['confirmarSenha'].value}

    this.resetPassService.alterarSenha(dados).subscribe({
      next:(msg)=>{
        this.toastr.success("Senha alterada com sucesso","Sucesso")
        this._router.navigate(['login'])
      },
      error:(erro)=>{
        console.log(erro)
        if(erro.error!=undefined){
        this.toastr.error(erro.error.message,"Erro")
        }
        else{
        this.toastr.error("Ocorreu um erro ao alterar a senha","Erro")
        }

      }
    })
  }
}








