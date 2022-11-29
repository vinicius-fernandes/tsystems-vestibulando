import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import IBanca from 'src/app/interfaces/IBanca';
import { BancasService } from 'src/app/services/bancas.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-gerencia-bancas',
  templateUrl: './gerencia-bancas.component.html',
  styleUrls: ['./gerencia-bancas.component.css']
})
export class GerenciaBancasComponent implements OnInit {

  constructor(private serviceBanca: BancasService, private toastr: ToastrService, private router: Router, private dialog: MatDialog) { }

  confirmarExclusao(id: number) {
    const dialogData = new ConfirmDialogModel(`Confirmar exclusão`, `Tem certeza de que deseja excluir esta banca?`)

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if ( dialogResult == true ) {
        this.excluirBanca(id)
      }
    })
  }

  bancas: IBanca[] = []

  ngOnInit(): void {
    this.serviceBanca.consultar().subscribe({
      next: data => this.bancas = data,
      error: erro => {
        console.log(erro)
        this.toastr.error("Não foi possível consultar as bancas.", "Erro")
        this.router.navigate(['app', 'home'])
      }
    })
  }

  excluirBanca(id: number) {

    this.serviceBanca.excluir(id).subscribe({next: () => {
      this.bancas = this.bancas.filter(b => b.id != id)
      this.toastr.success('Banca excluída com sucesso!', 'Sucesso')
    }, error: (erro) => {
      this.toastr.error('Esta banca não pode ser excluída, há objetos ligados a ela.', 'Erro')
      console.log(erro)
    }})
  }
}
