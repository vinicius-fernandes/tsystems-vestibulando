import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IPergunta from 'src/app/interfaces/IPergunta';
import IResposta from 'src/app/interfaces/IResposta';
import { QuestoesService } from 'src/app/services/questoes.service';

@Component({
  selector: 'app-edita-questoes',
  templateUrl: './edita-questoes.component.html',
  styleUrls: ['./edita-questoes.component.css'],
})
export class EditaQuestoesComponent implements OnInit {

  /* form: FormGroup; */

  respostas: IResposta[] = [];

  questao: IPergunta = {
    id: 0,
    corpo: '',
    banca: { id: 0, nome: '', sigla: '' },
    materia: { id: 0, nome: '' },
    respostas: this.respostas
  };


  constructor(
    private serviceQuestoes: QuestoesService,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {
    /* this.form = this.formBuilder.group({
      enunciado: new FormControl(''),
      idMateria: new FormControl(''),
      idBanca: new FormControl(''),
      resposta1: new FormControl(''),
      resposta2: new FormControl(''),
      resposta3: new FormControl(''),
      resposta4: new FormControl(''),
      resposta5: new FormControl(''),
      respostaCorreta: new FormControl(''),
    }); */
  }

  ngOnInit(): void {
    var routeParams = this.route.snapshot.paramMap;
    let id = parseInt(routeParams.get('id') || '0');
    this.serviceQuestoes.consultarPorId(id).subscribe({
      next: (data) => (this.questao = data),
      error: () => {
        this.toastr.error('Questão não encontrada.', 'Erro');
        window.history.back();
      },
    });
  }

  alterarQuestao() {
    console.log(this.questao.corpo)
    /* let respostas: IResposta[] = [];
    respostas.push({
      descricao: this.form.value.resposta1,
      correta: false,
    });
    respostas.push({
      descricao: this.form.value.resposta2,
      correta: false,
    });
    respostas.push({
      descricao: this.form.value.resposta3,
      correta: false,
    });
    respostas.push({
      descricao: this.form.value.resposta4,
      correta: false,
    });
    respostas.push({
      descricao: this.form.value.resposta5,
      correta: false,
    });

    respostas[this.form.value.respostaCorreta].correta = true;

    let dados: IPergunta = {
      corpo: this.form.value.enunciado,
      banca: { id: this.form.value.idBanca, nome: '', sigla: '' },
      materia: { id: this.form.value.idMateria, nome: '' },
      respostas: respostas
    }; */

    /* this.serviceQuestoes.salvar(this.questao).subscribe({
      next: () => {
        this.toastr.success('Pergunta adicionada com sucesso!', 'Sucesso');
        window.history.back();
      },
      error: () => {
        this.toastr.error('Não foi possível adicionar a pergunta.', 'Erro');
        window.history.back();
      },
    }); */
  }

}
