import { Component } from '@angular/core';
import { RespostasService } from '../../services/respostas.service';
import IResposta from 'src/app/interfaces/IResposta';

@Component({
  selector: 'app-respostas',
  templateUrl: './respostas.component.html',
  styleUrls: ['./respostas.component.css'],
})
export class RespostasComponent {
  msg: string = '';
  respostas: IResposta[] = [];
  resp: IResposta;

  constructor(private service: RespostasService) {
    this.resp = {
      descricao: '',
      correta: false,
    };
    this.consultarComoAdmin();
  }

  salvar(dados: any) {
    this.resp.correta = dados.correta;
    this.resp.descricao = dados.descricao;
    this.resp.pergunta = {
      id: dados.pergunta,
      corpo: '',
      bancas: { nome: '', sigla: '' },
      materias: { nome: '' },
    };
    this.service.salvar(this.resp).subscribe((data) => {
      this.msg = 'Resposta salva!';
      this.respostas = [...this.respostas, data];
    });
  }

  consultarComoAdmin() {
    this.service
      .consultarComoAdmin()
      .subscribe((data) => (this.respostas = data));
  }
}
