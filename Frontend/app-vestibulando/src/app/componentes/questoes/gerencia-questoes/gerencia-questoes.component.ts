import { Component } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { QuestoesService } from 'src/app/services/questoes.service';
import { ToastrService } from 'ngx-toastr';
import { PageEvent } from '@angular/material/paginator';
import { ContentObserver } from '@angular/cdk/observers';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-gerencia-questoes',
  templateUrl: './gerencia-questoes.component.html',
  styleUrls: ['./gerencia-questoes.component.css'],
})
export class GerenciaQuestoesComponent {
  constructor(
    private serviceQuestoes: QuestoesService,
    private toastr: ToastrService
  ) {}

  pergunta: IPergunta[] = [];
  totalElements: number = 0;


  ngOnInit(): void {

      this.obterPerguntas({page:'0',size:'10'})
  }

  obterPerguntas(params:any){
    this.serviceQuestoes.consultaPaginada(params).subscribe({
      next:(data)=>{
        this.pergunta = <IPergunta[]>data.content
        this.totalElements = data['totalElements']
      },
      error:(erro)=>{        this.toastr.error('Não foi possível obter as perguntas', 'Erro');
    }
    })


  }

  excluir(id: number) {
    this.serviceQuestoes.excluir(id).subscribe({
      next: () => {
        this.toastr.success('Pergunta excluida com sucesso!', 'Sucesso');
      },
      error: (err) => {
        console.log(err);
        this.toastr.error('Não foi possível excluir a pergunta.', 'Erro');
      },
    });
  }
}
