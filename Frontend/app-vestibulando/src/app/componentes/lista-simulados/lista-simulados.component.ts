import { Component, OnInit } from '@angular/core';
import { ListaSimuladosService } from "../../services/listaSimulados.service";
import ISimulado from "../../interfaces/ISimulado";
import {Router} from "@angular/router";

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

  constructor(private _router: Router, private service: ListaSimuladosService) {
  }

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

  redirecionarSimulado(id:number):void {
    this._router.navigate(['app/simulado',id])
  }

  redirecionarRanking(id:number):void {
    this._router.navigate(['app/rankingSimulado',id])
  }
}
