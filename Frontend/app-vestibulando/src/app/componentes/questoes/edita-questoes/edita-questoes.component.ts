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

  respostas: IResposta[] = [];

  form: FormGroup;

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
    this.form = this.formBuilder.group({
      respostaCorreta: new FormControl('')
    });
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
    if (this.form.value.respostaCorreta != '') {
      this.questao.respostas[0].correta = false
      this.questao.respostas[1].correta = false
      this.questao.respostas[2].correta = false
      this.questao.respostas[3].correta = false
      this.questao.respostas[4].correta = false
      this.questao.respostas[this.form.value.respostaCorreta].correta = true;
    } 

    this.serviceQuestoes.editar(this.questao).subscribe({
      next: () => {
        this.toastr.success('Questão editada com sucesso!', 'Sucesso');
        window.history.back();
      },
      error: () => {
        this.toastr.error('Não foi possível editar a questão.', 'Erro');
        window.history.back();
      },
    });
  }

}
