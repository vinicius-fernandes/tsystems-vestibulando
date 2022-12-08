import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IMateria from 'src/app/interfaces/IMateria';
import { MateriasService } from 'src/app/services/materias.service';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-gerencia-materias',
  templateUrl: './gerencia-materias.component.html',
  styleUrls: ['./gerencia-materias.component.css']
})
export class GerenciaMateriasComponent implements OnInit {

  loading:boolean=true
  materias: IMateria[] = []
  totalElements: number = 0;

  constructor(private serviceMateria: MateriasService, private toastr: ToastrService, private router: Router, private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.consultar({ page: '0', size: '14' })
  }

  consultar(params:any){
    this.serviceMateria.consultarPaginado(params).subscribe({
      next: (data) => {
        this.materias = <IMateria[]> data.content
        this.totalElements = data['totalElements']
      },
      error: erro => {
        console.log(erro)
        this.toastr.error("Não foi possível consultar as matérias.", "Erro")
        this.router.navigate(['app', 'home'])
      }
    }).add(()=>this.loading=false)

  }

  confirmarExclusao(id: number) {
    const dialogData = new ConfirmDialogModel(`Confirmar exclusão`, `Tem certeza de que deseja excluir esta matéria?`)

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult == true) {
        this.excluirMateria(id)
      }
    })
  }

  excluirMateria(id: number) {
    this.serviceMateria.excluir(id).subscribe({
      next: () => {
        this.materias = this.materias.filter(m => m.id != id)
        this.toastr.success('Matéria excluída com sucesso!', 'Sucesso')
      }, error: (erro) => {
        this.toastr.error('Esta matéria não pode ser excluída, há objetos ligados a ela.', 'Erro')
        console.log(erro)
      }
    })
  }
  nextPage(event: PageEvent) {
    const request = {
      page: event.pageIndex.toString(),
      size: event.pageSize.toString(),
    };
    this.loading=true
    this.materias=[]
    this.consultar(request)
  }
}
