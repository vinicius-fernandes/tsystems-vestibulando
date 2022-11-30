import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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

  materias: IMateria[] = []

  constructor(private serviceMateria: MateriasService, private toastr: ToastrService, private router: Router, private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.serviceMateria.consultar().subscribe({
      next: data => this.materias = data,
      error: erro => {
        console.log(erro)
        this.toastr.error("Não foi possível consultar as matérias.", "Erro")
        this.router.navigate(['app', 'home'])
      }
    })
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
}
