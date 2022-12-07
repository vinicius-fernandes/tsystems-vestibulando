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
import { PageEvent } from '@angular/material/paginator';
import IGeneric from 'src/app/interfaces/IGeneric';
import { BancasService } from 'src/app/services/bancas.service';
import { MateriasService } from 'src/app/services/materias.service';
import IPesquisaSimuladoDTO from 'src/app/interfaces/IPesquisaSimuladoDTO';

@Component({
  selector: 'app-lista-simulados',
  templateUrl: './lista-simulados.component.html',
  styleUrls: ['./lista-simulados.component.css']
})
export class ListaSimuladosComponent implements OnInit {

  simulados: ISimulado[] = []
  resultadosUsuario: INotasSimuladosUsuarios[] = []

  dataTest:IGeneric[]=[{id:1,name:'batata'},{id:2,name:'carne'}]

  materiasFiltro:IGeneric[]=[]
  bancasFiltro:IGeneric[]=[]

  bancasFiltroSelecionadas:number[]=[]
  materiasFiltroSelecionadas:number[]=[]
  isAdmin: boolean = false;
  totalElements: number = 0;

  loading:boolean=true
  constructor(private _router: Router,
    private service: ListaSimuladosService,
    private toastr: ToastrService,
    private router: Router,
    private respostasUsuarioService: RespostasUsuariosService,
    private bancasService:BancasService,
    private materiasService:MateriasService,
    private jwtService: JwtTokenService,
    private dialog: MatDialog) {
  }

  converterData(timestamp: number): string {
    return this.service.converterData(timestamp)
  }

  ngOnInit() {
    this.isAdmin = this.jwtService.checkAuthoritie('ROLE_ADMIN')
    this.obterRespostasUsuario()
    this.consultar({ page: '0', size: '3' })
    this.carregarBancas();
    this.carregarMaterias();
  }

  nextPage(event: PageEvent) {
    const request = {
      page: event.pageIndex.toString(),
      size: event.pageSize.toString(),
    };
    this.carregarSimulados(request);
  }

  carregarBancas(){
    this.bancasService.consultar().subscribe({
      next:(bancas)=>{
        this.bancasFiltro=bancas.map((b)=><IGeneric>{id:b.id,name:b.sigla})
      },
      error:(erro)=>{
        console.log(erro)
      }
    })
  }

  carregarMaterias(){
    this.materiasService.consultar().subscribe({
      next:(materias)=>{
        this.materiasFiltro=materias.map((m)=><IGeneric>{id:m.id,name:m.nome})
      },
      error:(erro)=>{
        console.log(erro)
      }
    })
  }

  consultar(params:any){
    this.service.listarPaginado(params).subscribe(
      {
        next:(value)=>{
          this.simulados=<ISimulado[]>value.content
          this.totalElements=value.totalElements
        },
        error:(erro)=>{
          console.log(erro)
          if (erro.status != 401) {

            this.toastr.error("Não foi possível consultar os simulados.", "Erro")
            this.router.navigate(['app', 'home'])
          }
        }
      }
    ).add(()=>this.loading=false)
  }

  pesquisar(params:any){
    let pesquisaDto : IPesquisaSimuladoDTO = {idBancas:this.bancasFiltroSelecionadas,idMaterias:this.materiasFiltroSelecionadas}

    this.service.pesquisar(params,pesquisaDto).subscribe({
      next:(value)=>{
        this.simulados=<ISimulado[]>value.content
        this.totalElements=value.totalElements
      },
      error:(erro)=>{
        console.log(erro)
        if (erro.status != 401) {

          this.toastr.error("Não foi possível consultar os simulados.", "Erro")
          this.router.navigate(['app', 'home'])
        }
      }
    }).add(()=>this.loading=false)
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

  filtroBancasMudou(selecionados:number[]){
    console.log(selecionados)
    this.bancasFiltroSelecionadas=selecionados;
    this.carregarSimulados({ page: '0', size: '3' })

  }

  filtroMateriaMudou(selecionados:number[]){
    console.log(selecionados)
    this.materiasFiltroSelecionadas=selecionados
    this.carregarSimulados({ page: '0', size: '3' })
  }

  carregarSimulados(params:any){
    this.loading=true
    this.simulados=[]
    if(this.bancasFiltroSelecionadas.length==0 && this.materiasFiltroSelecionadas.length==0){
      this.consultar(params);
      return
    }
    this.pesquisar(params)

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
