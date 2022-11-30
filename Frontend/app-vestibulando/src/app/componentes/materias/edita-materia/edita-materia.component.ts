import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IMateria from 'src/app/interfaces/IMateria';
import { MateriasService } from 'src/app/services/materias.service';

@Component({
  selector: 'app-edita-materia',
  templateUrl: './edita-materia.component.html',
  styleUrls: ['./edita-materia.component.css']
})
export class EditaMateriaComponent implements OnInit {

  materia: IMateria = {id: 0, nome: ""}

  constructor(private serviceMateria: MateriasService, private toastr: ToastrService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
      var routeParams = this.route.snapshot.paramMap
      let id = parseInt(routeParams.get('id') || '0')
      this.serviceMateria.consultarPorId(id).subscribe({
        next: data => this.materia = data,
        error: erro => {
          this.toastr.error('Matéria não encontrada.', 'Erro')
          this.router.navigate(['app', 'modmaterias'])
          console.log(erro)
        }
      })
  }

  editarMateria() {

    if ( this.materia.nome == null || this.materia.nome.length < 2 || this.materia.nome.length > 100 ) {
      this.toastr.error('O nome da matéria deve conter de 2 a 100 caracteres.', 'Erro')
      return
    }

    this.serviceMateria.editar(this.materia).subscribe({
      next: () => {
        this.toastr.success('Matéria alterada com sucesso!', 'Sucesso')
        this.router.navigate(['app', 'modmaterias'])
      }, error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro')
        this.router.navigate(['app', 'modmaterias'])
        console.log(erro)
      }
    })
  }
}
