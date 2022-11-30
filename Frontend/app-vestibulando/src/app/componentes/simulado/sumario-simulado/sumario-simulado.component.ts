import { Component, Input, Output, EventEmitter } from '@angular/core';
import IRespostaMarcada from 'src/app/interfaces/IRespostaMarcada';

@Component({
  selector: 'app-sumario-simulado',
  templateUrl: './sumario-simulado.component.html',
  styleUrls: ['./sumario-simulado.component.css']
})
export class SumarioSimuladoComponent {
  @Input() respostasMarcadas: IRespostaMarcada[] = []
  @Input() finalizado: boolean = false
  @Input() perguntasCorretas: number[] = []
  @Output() mudarPerguntaEvent = new EventEmitter<number>();

  changePergunta(numPerg: number) {
    this.mudarPerguntaEvent.emit(numPerg)
  }

  estaCorreta(idPergunta: number) {
    const corretas = this.perguntasCorretas.find(p => p == idPergunta)
    if (corretas != undefined)
      return true
    return false
  }
}
