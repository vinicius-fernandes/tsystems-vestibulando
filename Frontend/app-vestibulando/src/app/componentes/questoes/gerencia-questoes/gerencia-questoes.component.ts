import { Component } from '@angular/core';
import IPergunta from 'src/app/interfaces/IPergunta';
import { QuestoesService } from 'src/app/services/questoes.service';
import { ToastrService } from 'ngx-toastr';
import { PageEvent } from '@angular/material/paginator';
import { ContentObserver } from '@angular/cdk/observers';
import { ThisReceiver } from '@angular/compiler';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent, ConfirmDialogModel } from '../../confirm-dialog/confirm-dialog.component';
import IMateria from 'src/app/interfaces/IMateria';
import IBanca from 'src/app/interfaces/IBanca';
import { BancasService } from 'src/app/services/bancas.service';
import { MateriasService } from 'src/app/services/materias.service';

@Component({
  selector: 'app-gerencia-questoes',
  templateUrl: './gerencia-questoes.component.html',
  styleUrls: ['./gerencia-questoes.component.css'],
})
export class GerenciaQuestoesComponent {
  constructor(
    private serviceQuestoes: QuestoesService,
    private bancaService: BancasService,
    private materiaService: MateriasService,
    private toastr: ToastrService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  pergunta: IPergunta[] = [];
  totalElements: number = 0;
  corpo: string = '';
  idBanca: number = 0;
  idMateria: number = 0;
  materiasData: IMateria[] = [];
  bancasData: IBanca[] = [];

  ngOnInit(): void {
    this.obterPerguntas({ page: '0', size: '25' });

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

  obterPerguntas(params: any) {
    this.serviceQuestoes.consultaPaginada(params).subscribe({
      next: (data) => {
        this.pergunta = <IPergunta[]>data.content;
        this.totalElements = data['totalElements'];
      },
      error: () => {
        this.toastr.error('Não foi possível obter as perguntas', 'Erro');
      },
    });
  }

  obterFiltrado(params: any) {
    this.serviceQuestoes.consultaFiltrada(this.corpo, this.idBanca, this.idMateria, params).subscribe({
      next: (data) => {
        this.pergunta = <IPergunta[]>data.content;
        this.totalElements = data['totalElements'];
      },
      error: () => {
        this.toastr.error('Não foi possível obter as perguntas', 'Erro');
      },
    });
  }

  nextPage(event: PageEvent) {
    const request = {
      page: event.pageIndex.toString(),
      size: event.pageSize.toString(),
    };
    this.obterFiltrado(request);
  }

  confirmarExclusao(id: number) {
    const dialogData = new ConfirmDialogModel('Confirmar exclusão', 'Tem certeza de que deseja excluir esta pergunta?')
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    })

    dialogRef.afterClosed().subscribe(dialogResult => {
      if ( dialogResult == true ) {
        this.excluir(id)
      }
    })
  }

  excluir(id: number) {
    this.serviceQuestoes.excluir(id).subscribe({
      next: () => {
        this.toastr.success('Pergunta excluida com sucesso!', 'Sucesso');
        window.location.reload();
      },
      error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro');
      }
    });
  }
}
