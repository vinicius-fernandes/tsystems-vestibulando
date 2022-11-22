import { Component, OnInit } from '@angular/core';
import IMateria from 'src/app/interfaces/IMateria';
import { PerguntasService } from 'src/app/services/perguntas.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit{

  materias:IMateria[]=[]

constructor(private service:PerguntasService){

}
  ngOnInit(): void {
     this.service.consultar().subscribe(dados=>this.materias  = dados)
     console.log(JSON.stringify(this.materias.toString()))
  }



}
