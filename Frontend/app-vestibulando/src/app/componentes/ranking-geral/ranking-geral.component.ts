import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import IRankingSimulado from 'src/app/interfaces/IRankingSimulado';
import { RankingSimuladoService } from 'src/app/services/rankingSimulado.service';

@Component({
  selector: 'app-ranking-geral',
  templateUrl: './ranking-geral.component.html',
  styleUrls: ['./ranking-geral.component.css']
})
export class RankingGeralComponent implements OnInit {
  ranking: IRankingSimulado[] = [];
  totalElements: number = 0;

  constructor(private rankingService:RankingSimuladoService,    private toastr: ToastrService,
    ){

  }
  ngOnInit(): void {
    this.obterRanking({ page: '0', size: '25' })
  }

  obterRanking(params:any){
    this.rankingService.rankingGeral(params).subscribe(
      {
        next:(data)=>{
          this.ranking = <IRankingSimulado[]> data.content
          this.totalElements = data['totalElements']
        },
        error:(erro)=>{
          console.log(erro)
          if(erro.status!=401){
            this.toastr.error("Não foi possível carregar o ranking geral", "Erro")
          }
        }
      }
    )
  }
  nextPage(event: PageEvent) {
    const request = {
      page: event.pageIndex.toString(),
      size: event.pageSize.toString(),
    };

    this.obterRanking(request);
  }
}
