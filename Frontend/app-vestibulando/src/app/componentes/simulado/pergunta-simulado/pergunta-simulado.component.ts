import { Component, Input } from '@angular/core';
import IPerguntaDTO from 'src/app/interfaces/IPerguntaDTO';

@Component({
  selector: 'app-pergunta-simulado',
  templateUrl: './pergunta-simulado.component.html',
  styleUrls: ['./pergunta-simulado.component.css']
})
export class PerguntaSimuladoComponent {
  @Input() pergunta: IPerguntaDTO;
  @Input() numQuestao:number;
  respostaMarcada: number | null = null;
  constructor(){
    this.pergunta={idPergunta:0,corpo:'',respostas:[],banca:{nome:'',sigla:'teste'},materia:{nome:''}}
    this.numQuestao=0;

  }
}
