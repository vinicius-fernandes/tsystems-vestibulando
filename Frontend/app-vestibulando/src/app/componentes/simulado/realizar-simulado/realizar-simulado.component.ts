import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import ISimuladoDTO from 'src/app/interfaces/ISimuladoDTO';
import { SimuladoService } from 'src/app/services/simulado.service';

@Component({
  selector: 'app-realizar-simulado',
  templateUrl: './realizar-simulado.component.html',
  styleUrls: ['./realizar-simulado.component.css']
})
export class RealizarSimuladoComponent implements OnInit{

  simulado:ISimuladoDTO;
  constructor(private simuladoService:SimuladoService,private route: ActivatedRoute, private toastr: ToastrService){
    this.simulado = {perguntas:[],materias:[],id:0,bancas:[]}
  }
  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap
    let id = parseInt(routeParams.get('id') || '')
    this.simuladoService.realizar(id).subscribe(
      {
        next:(sim)=>{this.simulado = sim
        console.log(sim)
        },
        error:(erro)=>this.toastr.error(erro.error.message)
      }
    )
  }
}
