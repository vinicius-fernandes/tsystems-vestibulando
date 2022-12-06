import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BancasService } from 'src/app/services/bancas.service';
import IBanca from '../../../interfaces/IBanca';

@Component({
  selector: 'app-adiciona-banca',
  templateUrl: './adiciona-banca.component.html',
  styleUrls: ['./adiciona-banca.component.css']
})
export class AdicionaBancaComponent {

  constructor(private toastr: ToastrService, private serviceBanca: BancasService, private router: Router) { }

  nomeBanca = new FormControl()
  siglaBanca = new FormControl()
  dados: IBanca = { nome: '', sigla: '' }

  adicionarBanca() {
    let teveErro = false

    if (this.nomeBanca.value == null || this.nomeBanca.value.length > 250 || this.nomeBanca.value.length < 2) {
      teveErro = true
      this.toastr.error('O nome da banca deve conter de 2 a 250 caracteres.', 'Erro')
    }

    if (this.siglaBanca.value == null || this.siglaBanca.value.length > 50 || this.siglaBanca.value.length < 2) {
      teveErro = true
      this.toastr.error('A sigla da banca deve conter de 2 a 250 caracteres.', 'Erro')
    }

    if (teveErro) {
      return
    }

    this.dados.nome = this.nomeBanca.value
    this.dados.sigla = this.siglaBanca.value

    this.serviceBanca.salvar(this.dados).subscribe({
      next: () => {
        this.toastr.success('Banca adicionada com sucesso!', 'Sucesso')
        this.router.navigate(['app', 'modbancas'])
      },
      error: (erro) => {
        this.toastr.error(erro.error.message, 'Erro')
        this.router.navigate(['app', 'modbancas'])
      }
    })
  }
}
