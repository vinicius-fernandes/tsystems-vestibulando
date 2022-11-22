import { Component } from '@angular/core';
import IBanca from 'src/app/interfaces/IBanca';
import { BancasService } from 'src/app/services/bancas.service';

@Component({
  selector: 'app-gerencia-bancas',
  templateUrl: './gerencia-bancas.component.html',
  styleUrls: ['./gerencia-bancas.component.css']
})
export class GerenciaBancasComponent {

  constructor(private serviceBanca: BancasService) {

  }

  bancas: IBanca[] = []

  ngOnInit(): void {
    this.serviceBanca.consultar().subscribe(data => this.bancas = data)
  }

  excluirBanca(id: number) {
    this.serviceBanca.excluir(id)
    window.location.reload()
  }
}
