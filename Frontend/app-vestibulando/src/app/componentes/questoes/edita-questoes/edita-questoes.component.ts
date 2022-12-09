import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IBanca from 'src/app/interfaces/IBanca';
import IMateria from 'src/app/interfaces/IMateria';
import IPergunta from 'src/app/interfaces/IPergunta';
import IResposta from 'src/app/interfaces/IResposta';
import { BancasService } from 'src/app/services/bancas.service';
import { MateriasService } from 'src/app/services/materias.service';
import { QuestoesService } from 'src/app/services/questoes.service';

@Component({
  selector: 'app-edita-questoes',
  templateUrl: './edita-questoes.component.html',
  styleUrls: ['./edita-questoes.component.css'],
})
export class EditaQuestoesComponent implements OnInit {
  form: FormGroup;

  respostas: IResposta[] = [{descricao:"Carregando...",correta:false},{descricao:"Carregando...",correta:false},{descricao:"Carregando...",correta:false},{descricao:"Carregando...",correta:false},{descricao:"Carregando...",correta:false}];
  materiasData: IMateria[] = [];
  bancasData: IBanca[] = [];
  loading:boolean=true;
  editando:boolean=false
  questao: IPergunta = {
    id: 0,
    corpo: 'carregando ...',
    banca: { id: 0, nome: '', sigla: '' },
    materia: { id: 0, nome: '' },
    respostas: this.respostas,
  };

  constructor(
    private serviceQuestoes: QuestoesService,
    private bancaService: BancasService,
    private materiaService: MateriasService,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      respostaCorreta: new FormControl(''),
    });
  }

  ngOnInit(): void {
    var routeParams = this.route.snapshot.paramMap;
    let id = parseInt(routeParams.get('id') || '0');
    this.serviceQuestoes.consultarPorId(id).subscribe({
      next: (data) => (this.questao = data),
      error: () => {
        this.toastr.error('Questão não encontrada.', 'Erro');
        this.router.navigateByUrl('app/questoes');
      },
    }).add(()=>this.loading=false);

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

  alterarQuestao() {
    let teveErro = false;

    if (this.questao.materia.id == null) {
      teveErro = true;
      this.toastr.error('Favor, escolher uma matéria.', 'Erro');
    }

    if (this.questao.banca.id == null) {
      teveErro = true;
      this.toastr.error('Favor, escolher uma banca.', 'Erro');
    }

    if (
      this.questao.corpo == null ||
      this.questao.corpo.length > 3500 ||
      this.questao.corpo.length < 4
    ) {
      teveErro = true;
      this.toastr.error(
        'O enunciado da questão deve conter de 4 a 3500 caracteres.',
        'Erro'
      );
    }

    if (
      this.questao.respostas[0].descricao == null ||
      this.questao.respostas[0].descricao.length > 2500 ||
      this.questao.respostas[0].descricao.length < 1
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 1 a 2500 caracteres.',
        'Erro'
      );
    }

    if (
      this.questao.respostas[1].descricao == null ||
      this.questao.respostas[1].descricao.length > 2500 ||
      this.questao.respostas[1].descricao.length < 1
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 1 a 2500 caracteres.',
        'Erro'
      );
    }

    if (
      this.questao.respostas[2].descricao == null ||
      this.questao.respostas[2].descricao.length > 2500 ||
      this.questao.respostas[2].descricao.length < 1
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 1 a 2500 caracteres.',
        'Erro'
      );
    }

    if (
      this.questao.respostas[3].descricao == null ||
      this.questao.respostas[3].descricao.length > 2500 ||
      this.questao.respostas[3].descricao.length < 1
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 1 a 2500 caracteres.',
        'Erro'
      );
    }

    if (
      this.questao.respostas[4].descricao == null ||
      this.questao.respostas[4].descricao.length > 2500 ||
      this.questao.respostas[4].descricao.length < 1
    ) {
      teveErro = true;
      this.toastr.error(
        'Todas questões devem conter de 1 a 2500 caracteres.',
        'Erro'
      );
    }

    if (teveErro) {
      return;
    }

    if (this.form.value.respostaCorreta != '') {
      this.questao.respostas[0].correta = false;
      this.questao.respostas[1].correta = false;
      this.questao.respostas[2].correta = false;
      this.questao.respostas[3].correta = false;
      this.questao.respostas[4].correta = false;
      this.questao.respostas[this.form.value.respostaCorreta].correta = true;
    }
    this.editando=true
    this.serviceQuestoes.editar(this.questao).subscribe({
      next: () => {
        this.toastr.success('Questão editada com sucesso!', 'Sucesso');
        this.router.navigateByUrl('app/questoes');
      },
      error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro');
        this.router.navigateByUrl('app/questoes');
      },
    }).add(()=>this.editando=false);
  }
}
