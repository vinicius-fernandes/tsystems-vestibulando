import { Component, OnInit } from '@angular/core';
import IBanca from 'src/app/interfaces/IBanca';
import { BancasService } from 'src/app/services/bancas.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-gerencia-bancas',
  templateUrl: './gerencia-bancas.component.html',
  styleUrls: ['./gerencia-bancas.component.css']
})
export class GerenciaBancasComponent implements OnInit {

  constructor(private serviceBanca: BancasService, private toastr: ToastrService) {

  }

  bancas: IBanca[] = []

  ngOnInit(): void {
    this.serviceBanca.consultar().subscribe(data => this.bancas = data)
  }

  excluirBanca(id: number) {
    this.serviceBanca.excluir(id).subscribe({next: () => {
      this.bancas = this.bancas.filter(b => b.id != id)
      this.toastr.success('Banca excluída com sucesso!', 'Sucesso')
    }, error: (erro) => {
      this.toastr.error('Esta banca não pode ser excluída, há objetos ligados a ela.', 'Erro')
      console.log(erro)
    }})
  }
}
