import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IRole from 'src/app/interfaces/IRole';
import IUsuario from 'src/app/interfaces/IUsuario';
import { RolesService } from 'src/app/services/roles.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-edita-usuario',
  templateUrl: './edita-usuario.component.html',
  styleUrls: ['./edita-usuario.component.css']
})
export class EditaUsuarioComponent implements OnInit {

  usuario: IUsuario = { nome: "", email: "", senha: "", roles: [{ id: 0, authority: '' }] }
  roles: IRole[] = []
  public mostrarSenha: boolean = false

  constructor(private route: ActivatedRoute, private service: UsuarioService, private toastr: ToastrService, private router: Router, private roleService: RolesService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap
    let iduser = parseInt(routeParams.get('idusuario') || '')

    this.roleService.consultar().subscribe({
      next: (rolesData) => {
        this.roles = rolesData
      },
      error: (erro) => {
        console.log(erro)
        this.toastr.error("Não foi possível carregar os tipos de usuário.", "Erro")
        this.router.navigate(['/app/usuarios'])
      }
    })
    this.service.consultarbyId(iduser).subscribe({
      next: data => this.usuario = data,
      error: erro => {
        console.log(erro)
        this.toastr.error("Não foi possível consultar este usuário.", "Erro")
        this.router.navigate(['/app/usuarios'])
      }
    })
  }

  alterar() {

    let teveErro = false;

    if (this.usuario.nome.length < 3 || this.usuario.nome.indexOf(" ") <= 0 || this.usuario.nome.indexOf(" ") == (this.usuario.nome.length - 1)) {
      teveErro = true
      this.toastr.error('Insira o nome completo', 'Erro')
    }

    if (this.usuario.senha != null && (this.usuario.senha.length < 5 || this.usuario.senha.indexOf(" ") != -1)) {
      teveErro = true
      this.toastr.error('A senha deve conter no mínimo 6 caracteres e não pode conter espaços', 'Erro')
    }

    if (this.usuario.email.indexOf("@") == -1 || this.usuario.email.indexOf(".com") == -1) {
      teveErro = true
      this.toastr.error('Email Invalido', 'Erro')
    }
    if (this.usuario.roles![0].id == 0) {
      teveErro = true
      this.toastr.error("Selecione um tipo válido para o usuário", 'Erro')
    }

    if (teveErro) {
      return
    }
    this.service.alterar(this.usuario).subscribe({
      next: () => {
        this.toastr.success('Usuario alteardo com sucesso!', 'Sucesso')
        this.router.navigate(['/app/usuarios'])
      }, error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro')
      }
    })
  }
  mudaVisibilidadeSenha() {
    this.mostrarSenha = !this.mostrarSenha
  }
}
