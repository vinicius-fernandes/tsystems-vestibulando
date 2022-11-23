import { Component, OnInit } from '@angular/core';
import { ListaSimuladosService } from "../../services/listaSimulados.service";
import ISimulado from "../../interfaces/ISimulado";

@Component({
  selector: 'app-lista-simulados',
  templateUrl: './lista-simulados.component.html',
  styleUrls: ['./lista-simulados.component.css']
})
export class ListaSimuladosComponent implements OnInit{

  simulados:ISimulado[] = []

  constructor(private service: ListaSimuladosService) {
  }

  ngOnInit() {
    this.criarLista()
  }

  criarLista() {
    this.service.listar()
      .subscribe(dados => this.simulados = dados)
  }
}
