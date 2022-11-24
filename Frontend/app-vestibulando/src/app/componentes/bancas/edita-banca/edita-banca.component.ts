import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IBanca from 'src/app/interfaces/IBanca';
import { BancasService } from 'src/app/services/bancas.service';

@Component({
  selector: 'app-edita-banca',
  templateUrl: './edita-banca.component.html',
  styleUrls: ['./edita-banca.component.css']
})
export class EditaBancaComponent implements OnInit {

  banca: IBanca = {nome: '', sigla: '', id: 0}

  constructor(private serviceBanca: BancasService, private toastr: ToastrService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    var routeParams = this.route.snapshot.paramMap
    let id = parseInt(routeParams.get('id') || '0')
    this.serviceBanca.consultarPorId(id).subscribe({
      next: data => this.banca = data,
      error: () => {
        this.toastr.error('Banca não encontrada.', 'Erro')
        window.history.back()
      }
    })
  }

  editarBanca() {
    let teveErro = false;

    if ( this.banca.nome == null || this.banca.nome.length < 2 || this.banca.nome.length > 250 ) {
      teveErro = true
      this.toastr.error('O nome da banca deve conter de 2 a 250 caracteres.', 'Erro')
    }

    if ( this.banca.sigla == null || this.banca.sigla.length < 2 || this.banca.sigla.length > 50 ) {
      teveErro = true
      this.toastr.error('A sigla da banca deve conter de 2 a 250 caracteres.', 'Erro')
    }

    if ( teveErro ) {
      return
    }

    this.serviceBanca.editar(this.banca).subscribe({
      next: () => {
        this.toastr.success('Banca alterada com sucesso!', 'Sucesso')
        window.history.back()
      },
      error: () => {
        this.toastr.error('Não foi possível alterar a banca.', 'Erro')
        window.history.back()
      }
    })
  }
  
}
