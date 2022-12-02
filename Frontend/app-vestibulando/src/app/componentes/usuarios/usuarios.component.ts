import { Component, OnInit } from '@angular/core';
import IUsuario from 'src/app/interfaces/IUsuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastrService } from 'ngx-toastr';
import IRole from 'src/app/interfaces/IRole';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../confirm-dialog/confirm-dialog.component';
import { RolesService } from 'src/app/services/roles.service';


@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  public users: IUsuario[] = []
  public roles: IRole[] = []
  public pesquisa: String = ""
  public idRole: number = 0

  constructor(private service: UsuarioService, private toastr: ToastrService, private router: Router, private dialog: MatDialog, private roleService: RolesService) {
  }

  ngOnInit(): void {
    this.service.consultar().subscribe({
      next: data => this.users = data,
      error: () => {
        this.toastr.error("Não foi possível consultar os usuários.", "Erro")
        this.router.navigate(['app', 'home'])
      }
    })

    this.roleService.consultar().subscribe({
      next: (rolesData)=> {
        this.roles=rolesData
        console.log(rolesData)
      },
      error: (erro)=>console.log(erro)
    })
    
  }

  confirmarExclusao(id: number) {
    const dialogData = new ConfirmDialogModel(`Confirmar exclusão`, `Tem certeza de que deseja excluir este usuário?`)
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult == true) {
        this.excluir(id)
      }
    })
  }

  excluir(idUsuario: any) {
    this.service.excluir(idUsuario).subscribe({
      next: () => {
        this.users = this.users.filter(u => u.id != idUsuario)
        this.toastr.success('Usuario excluído com sucesso!', 'Sucesso')
      }, error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro')
      }
    })
  }

  checkRole(roles: IRole[], role: string) {
    if (roles.findIndex(r => r.authority == role) != -1) {
      return true
    }
    return false
  }

  pesquisar(){
    this.service.pesquisar(this.idRole, this.pesquisa).subscribe(

    )

  }
}
