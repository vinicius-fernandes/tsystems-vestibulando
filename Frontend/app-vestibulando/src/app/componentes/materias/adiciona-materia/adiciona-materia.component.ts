import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IMateria from 'src/app/interfaces/IMateria';
import { MateriasService } from 'src/app/services/materias.service';

@Component({
  selector: 'app-adiciona-materia',
  templateUrl: './adiciona-materia.component.html',
  styleUrls: ['./adiciona-materia.component.css']
})
export class AdicionaMateriaComponent {

  constructor(private toastr: ToastrService, private serviceMateria: MateriasService, private router: Router) { }

  nomeMateria = new FormControl()
  dados: IMateria = { nome: '' }

  adicionarMateria() {

    if (this.nomeMateria.value == null || this.nomeMateria.value.length > 100 || this.nomeMateria.value.length < 2) {
      this.toastr.error('O nome da matéria deve conter de 2 a 100 caracteres.', 'Erro')
      return
    }

    this.dados.nome = this.nomeMateria.value

    this.serviceMateria.salvar(this.dados).subscribe({
      next: () => {
        this.toastr.success('Matéria adicionada com sucesso!', 'Sucesso')
        this.router.navigate(['app', 'modmaterias'])
      }, error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro')
        this.router.navigate(['app', 'modmaterias'])
        console.log(erro)
      }
    })
  }
}
