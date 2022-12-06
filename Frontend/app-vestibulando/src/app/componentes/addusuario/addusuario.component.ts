import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IUsuario from 'src/app/interfaces/IUsuario';
import IRole from 'src/app/interfaces/IRole';
import { RolesService } from 'src/app/services/roles.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-addusuario',
  templateUrl: './addusuario.component.html',
  styleUrls: ['./addusuario.component.css']
})
export class AddusuarioComponent implements OnInit {

  usuario: IUsuario = { nome: "", email: "", senha: "",roles:[{id:0,authority:''}] }
  roles: IRole[] = []

  constructor(private router: Router, private service: UsuarioService, private toastr: ToastrService,private roleService: RolesService) { }
  ngOnInit(): void {
    this.roleService.consultar().subscribe({
      next: (rolesData)=> {
        this.roles=rolesData
        console.log(rolesData)
      },
      error: (erro)=>console.log(erro)
    })
  }

  salvar() {
    let teveErro = false;

    if (this.usuario.nome.length < 3 || this.usuario.nome.indexOf(" ") <= 0 || this.usuario.nome.indexOf(" ") == (this.usuario.nome.length - 1)) {
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
    if(this.usuario.roles![0].id==0){
      teveErro = true
      this.toastr.error("Selecione um tipo válido para o usuário",'Erro')
    }

    if (teveErro) {
      return
    }

    this.service.cadastrar(this.usuario).subscribe(
      {
        next: () => {
          this.toastr.success('Usuario adicionado com sucesso!', 'Sucesso')
          this.router.navigate(['/app/usuarios'])
        }, error: (erro) => {
          console.log(erro)
          this.toastr.error(erro.error.message, 'Erro')
        }
      }

    )

  }

}
