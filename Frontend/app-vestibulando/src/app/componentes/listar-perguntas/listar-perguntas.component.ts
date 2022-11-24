import { Component } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { ListarPerguntasService } from 'src/app/services/listar-perguntas.service';

@Component({
  selector: 'app-listar-perguntas',
  templateUrl: './listar-perguntas.component.html',
  styleUrls: ['./listar-perguntas.component.css']
})
export class ListarPerguntasComponent {
  msg: string = '';
  perguntas: IPergunta[] = [];
  constructor(private service: ListarPerguntasService) {
    this.listar();
  }

  listar() {
    this.service
      .listar()
      .subscribe((data) => (this.perguntas = data));
  }

}
