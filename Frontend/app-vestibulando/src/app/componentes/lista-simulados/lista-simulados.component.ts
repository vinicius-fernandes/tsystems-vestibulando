import { Component, OnInit } from '@angular/core';
import { ListaSimuladosService } from "../../services/listaSimulados.service";
import ISimulado from "../../interfaces/ISimulado";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-lista-simulados',
  templateUrl: './lista-simulados.component.html',
  styleUrls: ['./lista-simulados.component.css']
})
export class ListaSimuladosComponent implements OnInit{

  simulados:ISimulado[] = []

  converterData(timestamp:number):string {
    return this.service.converterData(timestamp)
  }

  constructor(private _router: Router, private service: ListaSimuladosService,  private toastr: ToastrService) { }

  ngOnInit() {
    this.criarLista()
  }

  criarLista() {
    this.service.listar()
      .subscribe(dados => this.simulados = dados.sort(
        (a,b) => {
          return b.createdAt - a.createdAt
        })
      )
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
