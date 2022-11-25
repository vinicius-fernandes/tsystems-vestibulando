import { Component, OnInit } from '@angular/core';
import IUsuario from 'src/app/interfaces/IUsuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent {

  public users: IUsuario[] = []

  constructor(private service: UsuarioService, private toastr: ToastrService, private router: Router) {
    this.consultar()
  }
  consultar() {
    this.service.consultar().subscribe({
      next: data => this.users = data,
      error: erro => {
        console.log(erro)
        this.toastr.error("Não foi possível consultar os usuários.", "Erro")
        this.router.navigate(['app', 'home'])
      }
    })
  }
  excluir(idUsuario: any) {
    this.service.excluir(idUsuario).subscribe({next: () => {
      this.users = this.users.filter(u => u.id != idUsuario)
      this.toastr.success('Usuario excluído com sucesso!', 'Sucesso')
    }, error: (erro) => {
    this.toastr.error('Este usuário não pode ser excluído, há objetos ligados a ele.', 'Erro')
    }})
  }

  user(tipo:string){
    if(tipo == "CLIENTE"){
      return true;
    }
    return false;
  }
  adm(tipo:string){
    if(tipo == "ADMINISTRADOR"){
      return true;
    }
    return false;
  }

}
