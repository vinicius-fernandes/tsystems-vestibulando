import { Component } from '@angular/core';
import { RespostasService } from '../../services/respostas.service';
import IResposta from 'src/app/interfaces/IResposta';

@Component({
  selector: 'app-respostas',
  templateUrl: './respostas.component.html',
  styleUrls: ['./respostas.component.css']
})
export class RespostasComponent {

  msg: string = ''
  respostas: IResposta[] = []
  constructor(private service: RespostasService){
    this.consultarComoAdmin()
  }

  consultarComoAdmin() {
    this.service.consultarComoAdmin().subscribe(data => this.respostas = data)
  }

  

}
