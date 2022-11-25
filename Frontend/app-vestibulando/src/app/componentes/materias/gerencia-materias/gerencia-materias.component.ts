import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import IMateria from 'src/app/interfaces/IMateria';
import { MateriasService } from 'src/app/services/materias.service';

@Component({
  selector: 'app-gerencia-materias',
  templateUrl: './gerencia-materias.component.html',
  styleUrls: ['./gerencia-materias.component.css']
})
export class GerenciaMateriasComponent implements OnInit {

  materias: IMateria[] = []

  constructor(private serviceMateria: MateriasService, private toastr: ToastrService) {

  }

  ngOnInit(): void {
    this.serviceMateria.consultar().subscribe({
      next: data => this.materias = data,
      error: erro => {
        console.log(erro)
        this.toastr.error("Não foi possível consultar as matérias.", "Erro")
      }
    })
  }

  excluirMateria(id: number) {
    this.serviceMateria.excluir(id).subscribe({
      next: () => {
        this.materias = this.materias.filter(m => m.id != id)
        this.toastr.success('Matéria excluída com sucesso!', 'Sucesso')
      }, error: erro => {
        this.toastr.error('Esta matéria não pode ser excluída, há objetos ligados a ela.', 'Erro')
        console.log(erro)
      }
    })
  }
}
