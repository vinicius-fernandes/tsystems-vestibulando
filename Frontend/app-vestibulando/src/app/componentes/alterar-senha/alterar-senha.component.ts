import { FocusMonitor } from '@angular/cdk/a11y';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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

  constructor(private _router: Router, private formBuilder: FormBuilder,private toastr: ToastrService,private resetPassService:ResetPasswordService,private route: ActivatedRoute){
    this.form = this.formBuilder.group(
      {
        password: new FormControl("", [ Validators.required])
      }
    )
  }

  alterarSenha(){
    const routeParams = this.route.snapshot.paramMap

    let token = routeParams.get('token') || ''

    console.log(token)

    let dados : IAlterarSenha = {senha:this.form.controls['password'].value,token:token}

    this.resetPassService.alterarSenha(dados).subscribe({
      next:(msg)=>{
        this.toastr.success("Senha alterada com sucesso","Sucesso")
        this._router.navigate(['login'])
      },
      error:(erro)=>{
        console.log(erro)
        this.toastr.error("Ops ocorreu um erro ao alterar a senha, é possível que o token tenha expirado requisite uma nova mudança de senha!","Erro")
      }
    })
  }
}
