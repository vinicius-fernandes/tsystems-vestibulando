import { Component } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { QuestoesService } from 'src/app/services/questoes.service';
import { ToastrService } from 'ngx-toastr';

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

  ngOnInit(): void {
    this.serviceQuestoes
      .consultar()
      .subscribe((data) => (this.pergunta = data));
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
