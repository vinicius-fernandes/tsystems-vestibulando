import { Component, OnInit } from '@angular/core';
import IUsuario from 'src/app/interfaces/IUsuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastrService } from 'ngx-toastr';
import IRole from 'src/app/interfaces/IRole';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent {

  public users: IUsuario[] = []

  constructor(private service: UsuarioService, private toastr: ToastrService) {
    this.consultar()
  }
  consultar() {
    this.service.consultar().subscribe(data => this.users = data)
  }
  excluir(idUsuario: any) {
    this.service.excluir(idUsuario).subscribe({next: () => {
      this.users = this.users.filter(u => u.id != idUsuario)
      this.toastr.success('Usuario excluída com sucesso!', 'Sucesso')
    }, error: (erro) => {
    this.toastr.error('Este usuário não pode ser excluída, há objetos ligados a ela.', 'Erro')
    }})
  }

  checkRole(roles:IRole[],role:string){
    if(roles.findIndex(r=>r.authority==role)!= -1){
      return true
    }
    return false
  }


}
