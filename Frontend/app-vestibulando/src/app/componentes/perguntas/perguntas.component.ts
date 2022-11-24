import { Component } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { PerguntasService } from 'src/app/services/perguntas.service';

@Component({
  selector: 'app-perguntas',
  templateUrl: './perguntas.component.html',
  styleUrls: ['./perguntas.component.css'],
})
export class PerguntasComponent {
  msg: string = '';
  perguntas: IPergunta[] = [];
  constructor(private service: PerguntasService) {
    this.listar();
  }

  listar() {
    this.service
      .listar()
      .subscribe((data) => (this.perguntas = data));
  }
}
