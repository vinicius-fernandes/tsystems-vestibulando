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
  teste: IResposta;

  constructor(private service: RespostasService) {
    this.teste = {
      descricao: '',
      correta: false,
    };
    this.consultarComoAdmin();
  }

  salvar(dados: any) {
    this.teste.correta = dados.correta;
    this.teste.descricao = dados.descricao;
    this.teste.pergunta = {
      id: dados.pergunta,
      corpo: '',
      bancas: { nome: '', sigla: '' },
      materias: { nome: '' },
    };
    this.service.salvar(this.teste).subscribe((data) => {
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
