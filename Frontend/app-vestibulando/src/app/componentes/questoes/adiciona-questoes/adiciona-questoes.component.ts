import { Component, OnInit } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { QuestoesService } from 'src/app/services/questoes.service';
import { ToastrService } from 'ngx-toastr';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import IResposta from 'src/app/interfaces/IResposta';
import { Router } from '@angular/router';
import IMateria from 'src/app/interfaces/IMateria';
import { MateriasService } from 'src/app/services/materias.service';
import IBanca from 'src/app/interfaces/IBanca';
import { BancasService } from 'src/app/services/bancas.service';

@Component({
  selector: 'app-adiciona-questoes',
  templateUrl: './adiciona-questoes.component.html',
  styleUrls: ['./adiciona-questoes.component.css'],
})
export class AdicionaQuestoesComponent implements OnInit {
  form: FormGroup;

  materiasData: IMateria[] = [];
  bancasData: IBanca[] = [];

  constructor(
    private serviceQuestoes: QuestoesService,
    private bancaService: BancasService,
    private materiaService: MateriasService,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private router: Router
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

  ngOnInit(): void {
    this.bancaService.consultar().subscribe({
      next: (bancas) => {
        this.bancasData = bancas;
      },
      error: (error) => {
        console.log(error);
        this.toastr.error('Não foi possível consultar as bancas.', 'Erro');
        this.router.navigate(['app', 'home']);
      },
    });
    this.materiaService.consultar().subscribe({
      next: (materias) => {
        this.materiasData = materias;
      },
      error: (error) => {
        console.log(error);
        this.toastr.error('Não foi possível consultar as matérias.', 'Erro');
        this.router.navigate(['app', 'home']);
      },
    });
  }

  salvarQuestao() {
    let teveErro = false;

    if (this.form.value.idMateria == null) {
      teveErro = true;
      this.toastr.error('Favor, escolher uma matéria.', 'Erro');
    }

    if (this.form.value.idBanca == null) {
      teveErro = true;
      this.toastr.error('Favor, escolher uma banca.', 'Erro');
    }

    if (
      this.form.value.enunciado == null ||
      this.form.value.enunciado.length > 350 ||
      this.form.value.enunciado.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'O enunciado da questão deve conter de 4 a 350 caracteres.',
        'Erro'
      );
    }

    if (
      this.form.value.resposta1 == null ||
      this.form.value.resposta1.length > 350 ||
      this.form.value.resposta1.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 4 a 350 caracteres.',
        'Erro'
      );
    }

    if (
      this.form.value.resposta2 == null ||
      this.form.value.resposta2.length > 350 ||
      this.form.value.resposta2.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 4 a 350 caracteres.',
        'Erro'
      );
    }

    if (
      this.form.value.resposta3 == null ||
      this.form.value.resposta3.length > 350 ||
      this.form.value.resposta3.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 4 a 350 caracteres.',
        'Erro'
      );
    }

    if (
      this.form.value.resposta4 == null ||
      this.form.value.resposta4.length > 350 ||
      this.form.value.resposta4.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 4 a 350 caracteres.',
        'Erro'
      );
    }

    if (
      this.form.value.resposta5 == null ||
      this.form.value.resposta5.length > 350 ||
      this.form.value.resposta5.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 4 a 350 caracteres.',
        'Erro'
      );
    }

    if (this.form.value.respostaCorreta == '') {
      teveErro = true;
      this.toastr.error('Favor, escolher a alternativa correta.', 'Erro');
    }

    if (teveErro) {
      return;
    }

    let respostas: IResposta[] = [];
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
      respostas: respostas,
    };

    this.serviceQuestoes.salvar(dados).subscribe({
      next: () => {
        this.toastr.success('Pergunta adicionada com sucesso!', 'Sucesso');
        this.router.navigateByUrl('app/questoes');
      },
      error: () => {
        console.log();
        this.toastr.error('Não foi possível adicionar a pergunta.', 'Erro');
        this.router.navigateByUrl('app/questoes');
      },
    });
  }
}
