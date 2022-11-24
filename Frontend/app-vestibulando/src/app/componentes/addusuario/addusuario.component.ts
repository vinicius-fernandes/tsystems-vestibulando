import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IUsuario from 'src/app/interfaces/IUsuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-addusuario',
  templateUrl: './addusuario.component.html',
  styleUrls: ['./addusuario.component.css']
})
export class AddusuarioComponent {

  usuario: IUsuario = { nome: "", email: "", tipo: "", senha: "" }

  constructor(private router: Router, private service: UsuarioService, private toastr: ToastrService) { }

  salvar() {
    let teveErro = false;

    if (this.usuario.nome.length < 3 || this.usuario.nome.indexOf(" ") == -1) {
      teveErro = true
      this.toastr.error('Insira o nome completo', 'Erro')
    }

    if (this.usuario.senha == null || this.usuario.senha.length < 5 || this.usuario.senha.indexOf(" ") != -1) {
      teveErro = true
      this.toastr.error('A senha deve conter no mínimo 6 caracteres e não pode conter espaços', 'Erro')
    }

    if (this.usuario.email.indexOf("@") == -1 || this.usuario.email.indexOf(".com") == -1) {
      teveErro = true
      this.toastr.error('Email Invalido', 'Erro')
    }

    if (teveErro) {
      return
    }

    this.service.salvar(this.usuario).subscribe(
      {next: () => {
      this.toastr.success('Usuario adicionado com sucesso!', 'Sucesso')
      this.router.navigate(['/app/usuarios'])
    }, error: (erro) => {
      this.toastr.error('Este usuário não pode ser adicionado, verifique todos os campos', 'Erro')
    }
  }



    )

}

}
