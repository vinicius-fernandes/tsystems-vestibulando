import { Component } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { QuestoesService } from 'src/app/services/questoes.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import IResposta from 'src/app/interfaces/IResposta';

@Component({
  selector: 'app-adiciona-questoes',
  templateUrl: './adiciona-questoes.component.html',
  styleUrls: ['./adiciona-questoes.component.css'],
})
export class AdicionaQuestoesComponent {
  form: FormGroup;

  constructor(
    private serviceQuestoes: QuestoesService,
    private toastr: ToastrService,
    private formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      enunciado: new FormControl(''),
      idMateria: new FormControl(''),
      idBanca: new FormControl(''),
      resposta1: new FormControl(''),
      resposta2: new FormControl(''),
      resposta3: new FormControl(''),
      resposta4: new FormControl(''),
      resposta5: new FormControl(''),
      respostaCorreta: new FormControl(''),
    });
  }

  salvarQuestao() {
    let respostas: IResposta[] = []
    respostas.push({
      descricao: this.form.value.resposta1,
      correta: false,
    })
    respostas.push({
      descricao: this.form.value.resposta2,
      correta: false,
    })
    respostas.push({
      descricao: this.form.value.resposta3,
      correta: false,
    })
    respostas.push({
      descricao: this.form.value.resposta4,
      correta: false,
    })
    respostas.push({
      descricao: this.form.value.resposta5,
      correta: false,
    })

    respostas[this.form.value.respostaCorreta].correta=true
    console.log(respostas)
    
    let dados: IPergunta = {
      corpo: this.form.value.enunciado,
      banca: { id: this.form.value.idBanca, nome: '', sigla: '' },
      materia: { id: this.form.value.idMateria, nome: '' },
      respostas: respostas,
    };

    console.log(`Enunciado: ${dados}`)
    
    this.serviceQuestoes.salvar(dados).subscribe({
      next: () => {
        this.toastr.success('Pergunta adicionada com sucesso!', 'Sucesso');
        window.history.back();
      },
      error: () => {
        this.toastr.error('Não foi possível adicionar a pergunta.', 'Erro');
        window.history.back();
      }
    });

  }
}
