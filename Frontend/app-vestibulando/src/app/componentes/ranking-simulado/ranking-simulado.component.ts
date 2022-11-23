import { Component, OnInit } from '@angular/core';
import IRankingSimulado from "../../interfaces/IRankingSimulado";
import {RankingSimuladoService} from "../../services/rankingSimulado.service";

@Component({
  selector: 'app-ranking-simulado',
  templateUrl: './ranking-simulado.component.html',
  styleUrls: ['./ranking-simulado.component.css']
})
export class RankingSimuladoComponent implements OnInit{

  ranking:IRankingSimulado[] = []

  constructor(private service: RankingSimuladoService) { }

  ngOnInit() {
    this.fazerConsulta(1)
    console.log(this.ranking)
  }

  fazerConsulta(idSimulado:number):void {
    this.service.consultar(idSimulado)
      .subscribe(dados => {
        this.ranking = dados.sort(
          (a, b) => {
            return b.acertosUsuario - a.acertosUsuario
          }
        )
      }
    )
  }
}
