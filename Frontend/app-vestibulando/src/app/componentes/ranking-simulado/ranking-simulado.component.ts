import { Component, OnInit } from '@angular/core';
import IRankingSimulado from "../../interfaces/IRankingSimulado";
import {RankingSimuladoService} from "../../services/rankingSimulado.service";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ranking-simulado',
  templateUrl: './ranking-simulado.component.html',
  styleUrls: ['./ranking-simulado.component.css']
})
export class RankingSimuladoComponent implements OnInit{

  ranking:IRankingSimulado[] = []

  id:number = 0

  constructor(private service: RankingSimuladoService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.id = params['id'])
    this.fazerConsulta(this.id)
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
