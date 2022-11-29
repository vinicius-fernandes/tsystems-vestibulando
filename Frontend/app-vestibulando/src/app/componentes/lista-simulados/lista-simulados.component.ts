import { Component, OnInit } from '@angular/core';
import { ListaSimuladosService } from "../../services/listaSimulados.service";
import ISimulado from "../../interfaces/ISimulado";
import {Router} from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import JwtTokenService from "../../services/jwt-token.service";
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../confirm-dialog/confirm-dialog.component';


@Component({
  selector: 'app-lista-simulados',
  templateUrl: './lista-simulados.component.html',
  styleUrls: ['./lista-simulados.component.css']
})
export class ListaSimuladosComponent implements OnInit {

  simulados:ISimulado[] = []

  isAdmin:boolean=false;

  constructor(private _router: Router,
              private service: ListaSimuladosService,
              private toastr: ToastrService,
              private router: Router,
              private jwtService:JwtTokenService,
              private dialog: MatDialog) {
  }

  converterData(timestamp:number):string {
    return this.service.converterData(timestamp)
  }

  ngOnInit() {
    this.isAdmin = this.jwtService.checkAuthoritie('ROLE_ADMIN')
    this.criarLista()
  }

  criarLista() {
    this.service.listar()
      .subscribe({
        next: dados => this.simulados = dados.sort(
        (a,b) => {
          return b.createdAt - a.createdAt
        }),
        error: erro => {
          console.log(erro)
          this.toastr.error("Não foi possível consultar os simulados.", "Erro")
          this.router.navigate(['app', 'home'])
        }
    })
  }

  confirmarExclusaoSimulado(id: number) {
    const dialogData = new ConfirmDialogModel("Confirmar exclusão", "Tem certeza de que deseja excluir este simulado?")
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if ( dialogResult == true ) {
        this.excluirSimulado(id)
      }
    })
  }

  excluirSimulado(id:number) {
    this.service.excluir(id).subscribe({next: () => {
        this.simulados = this.simulados.filter(b => b.id != id)
        this.toastr.success('Simulado excluído com sucesso!', 'Sucesso')
      }, error: (erro) => {
        this.toastr.error('Este simulado não pode ser excluído.', 'Erro')
        console.log(erro)
      }})
  }

  redirecionarSimulado(id:number):void {
    this._router.navigate(['app/simulados/realizar',id])
  }

  redirecionarRanking(id:number):void {
    this._router.navigate(['app/simulados/rankingSimulado',id])
  }
}
