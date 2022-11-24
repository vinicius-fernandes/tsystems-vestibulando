import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RespostasUsuariosService } from 'src/app/services/respostas-usuarios.service';

@Component({
  selector: 'app-resultado-simulado',
  templateUrl: './resultado-simulado.component.html',
  styleUrls: ['./resultado-simulado.component.css']
})
export class ResultadoSimuladoComponent implements OnInit{

  totalResultados:number = 0

  constructor(private service:RespostasUsuariosService,private route: ActivatedRoute, private toastr: ToastrService){

  }
  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap
    let idSim = parseInt(routeParams.get('idSimulado') || '')
    let idUser = parseInt(routeParams.get('idUser') || '')

    this.service.resultado(idSim,idUser).subscribe({
      next:(res)=>{this.totalResultados=res.nota},
      error:(erro)=>{
        console.log(erro)
        this.toastr.error(erro.error.message)
      }
    })
  }
}
