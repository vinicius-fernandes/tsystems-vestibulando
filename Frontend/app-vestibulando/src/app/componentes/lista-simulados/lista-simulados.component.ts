import { Component, OnInit } from '@angular/core';
import { ListaSimuladosService } from "../../services/listaSimulados.service";
import ISimulado from "../../interfaces/ISimulado";
import {Router} from "@angular/router";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-lista-simulados',
  templateUrl: './lista-simulados.component.html',
  styleUrls: ['./lista-simulados.component.css']
})
export class ListaSimuladosComponent implements OnInit {

  simulados:ISimulado[] = []

  converterData(timestamp:number):string {
    return this.service.converterData(timestamp)
  }

  constructor(private _router: Router, private service: ListaSimuladosService, private toastr: ToastrService, private router: Router) {
  }

  ngOnInit() {
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

  redirecionarSimulado(id:number):void {
    this._router.navigate(['app/simulados/realizar',id])
  }

  redirecionarRanking(id:number):void {
    this._router.navigate(['app/simulados/rankingSimulado',id])
  }
}
