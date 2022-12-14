import { Component, Input, Output, EventEmitter  } from '@angular/core';
import { MatRadioChange } from '@angular/material/radio';
import IPerguntaDTO from 'src/app/interfaces/IPerguntaDTO';
import IRespostaMarcada from 'src/app/interfaces/IRespostaMarcada';

@Component({
  selector: 'app-pergunta-simulado',
  templateUrl: './pergunta-simulado.component.html',
  styleUrls: ['./pergunta-simulado.component.css']
})
export class PerguntaSimuladoComponent {
  @Input() pergunta: IPerguntaDTO;
  @Input() numQuestao:number;
  @Input() finalizado: boolean =false;
  @Input() perguntasCorretas: number[]=[];
  @Output() respostaMarcadaEvent = new EventEmitter<IRespostaMarcada>()
  resposta: number =0

  constructor(){
    this.pergunta={idPergunta:0,corpo:'',respostas:[],banca:{nome:'',sigla:'teste'},materia:{nome:''}}
    this.numQuestao=0;
  }

  changeResposta(event: { value: any; }){

    let resp : IRespostaMarcada ={idRespostaMarcada:event.value,numeroPergunta:this.numQuestao,idPergunta:this.pergunta.idPergunta}
    this.respostaMarcadaEvent.emit(resp)
  }

  estaCorreta(){
    const corretas = this.perguntasCorretas.find(p=> p == this.pergunta.idPergunta)
    if(corretas!=undefined)
      return true
    return false
  }

}
