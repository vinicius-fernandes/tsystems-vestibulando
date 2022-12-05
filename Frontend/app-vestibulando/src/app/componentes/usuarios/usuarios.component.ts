import { Component } from '@angular/core';
import IUsuario from 'src/app/interfaces/IUsuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastrService } from 'ngx-toastr';
import IRole from 'src/app/interfaces/IRole';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../confirm-dialog/confirm-dialog.component';
import { PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent {

  public users: IUsuario[] = []
  totalElements: number = 0;
  constructor(private service: UsuarioService, private toastr: ToastrService, private router: Router, private dialog: MatDialog) {
    this.consultar({ page: '0', size: '8' })
  }
  consultar(params:any) {
    this.service.consultarPaginado(params).subscribe({
      next: data => {this.users = <IUsuario[]> data.content
        this.totalElements = data['totalElements']
      },
      error: erro => {
        this.toastr.error("Não foi possível consultar os usuários.", "Erro")
        this.router.navigate(['app', 'home'])
      }
    })
  }

  confirmarExclusao(id: number) {
    const dialogData = new ConfirmDialogModel(`Confirmar exclusão`, `Tem certeza de que deseja excluir este usuário?`)
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if ( dialogResult == true ) {
        this.excluir(id)
      }
    })
  }

  excluir(idUsuario: any) {
    this.service.excluir(idUsuario).subscribe({next: () => {
      this.users = this.users.filter(u => u.id != idUsuario)
      this.toastr.success('Usuario excluído com sucesso!', 'Sucesso')
    }, error: (erro) => {
    this.toastr.error(erro.error.message, 'Erro')
    }})
  }

  checkRole(roles:IRole[],role:string){
    if(roles.findIndex(r=>r.authority==role)!= -1){
      return true
    }
    return false
  }

  nextPage(event: PageEvent) {
    const request = {
      page: event.pageIndex.toString(),
      size: event.pageSize.toString(),
    };

    this.consultar(request);
  }
}
