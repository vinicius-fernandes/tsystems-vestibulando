import { Component, OnInit } from '@angular/core';
import { ListaSimuladosService } from "../../services/listaSimulados.service";
import ISimulado from "../../interfaces/ISimulado";
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import JwtTokenService from "../../services/jwt-token.service";
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../confirm-dialog/confirm-dialog.component';
import { RespostasUsuariosService } from 'src/app/services/respostas-usuarios.service';
import INotasSimuladosUsuarios from 'src/app/interfaces/INotasSimuladosUsuario';

@Component({
  selector: 'app-lista-simulados',
  templateUrl: './lista-simulados.component.html',
  styleUrls: ['./lista-simulados.component.css']
})
export class ListaSimuladosComponent implements OnInit {

  simulados: ISimulado[] = []
  resultadosUsuario: INotasSimuladosUsuarios[] = []

  isAdmin: boolean = false;

  loading:boolean=true
  constructor(private _router: Router,
    private service: ListaSimuladosService,
    private toastr: ToastrService,
    private router: Router,
    private respostasUsuarioService: RespostasUsuariosService,
    private jwtService: JwtTokenService,
    private dialog: MatDialog) {
  }

  converterData(timestamp: number): string {
    return this.service.converterData(timestamp)
  }

  ngOnInit() {
    this.isAdmin = this.jwtService.checkAuthoritie('ROLE_ADMIN')
    this.obterRespostasUsuario()
    this.criarLista()
  }

  encontrarSimuladoRespondido(idSimulado: number) {
    const res = this.resultadosUsuario.find(r => r.idSimulado == idSimulado);

    if (res != undefined) {
      return `Você já respondeu esse simulado e sua nota foi ${res.nota}`
    }
    return ''
  }

  obterRespostasUsuario() {
    const user = this.jwtService.getTokenDecoded()
    if (user != null) {
      this.respostasUsuarioService.notasSimuladosUsuario(user.userId).subscribe(
        {
          next: (resps) => this.resultadosUsuario = resps,
          error: (erro) => {
            this.toastr.error("Ocorreu um erro ao identificar os simulados respondidos pelo usuário atual", "erro")
          }
        }
      )
    }
  }

  criarLista() {
    this.service.listar()
      .subscribe({
        next: dados => this.simulados = dados.sort(
          (a, b) => {
            return b.createdAt - a.createdAt
          }),
        error: erro => {
          console.log(erro)
          if (erro.status != 401) {

            this.toastr.error("Não foi possível consultar os simulados.", "Erro")
            this.router.navigate(['app', 'home'])
          }
        }
      }).add(()=>this.loading=false)
  }

  confirmarExclusaoSimulado(id: number) {
    const dialogData = new ConfirmDialogModel("Confirmar exclusão", "Tem certeza de que deseja excluir este simulado?")
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult == true) {
        this.excluirSimulado(id)
      }
    })
  }


  confirmarRealizacaoSimulado(id: number) {
    const dialogData = new ConfirmDialogModel("Confirmar realização", "Tem certeza de que deseja realizar este simulado? Os resultado anteriores serão sobrescritos")
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if (dialogResult == true) {
        this.redirecionarSimulado(id)
      }
    })
  }

  excluirSimulado(id: number) {
    this.service.excluir(id).subscribe({
      next: () => {
        this.simulados = this.simulados.filter(b => b.id != id)
        this.toastr.success('Simulado excluído com sucesso!', 'Sucesso')
      }, error: (erro) => {
        this.toastr.error('Este simulado não pode ser excluído.', 'Erro')
        console.log(erro)
      }
    })
  }

  redirecionarSimulado(id: number): void {
    this._router.navigate(['app/simulados/realizar', id])
  }

  redirecionarRanking(id: number): void {
    this._router.navigate(['app/simulados/rankingSimulado', id])
  }
}
