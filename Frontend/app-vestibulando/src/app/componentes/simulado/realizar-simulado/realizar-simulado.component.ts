import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import IGeneric from 'src/app/interfaces/IGeneric';
import IRespostaMarcada from 'src/app/interfaces/IRespostaMarcada';
import IRespostaUsuario from 'src/app/interfaces/IRespostaUsuario';
import ISimuladoDTO from 'src/app/interfaces/ISimuladoDTO';
import { RespostasUsuariosService } from 'src/app/services/respostas-usuarios.service';
import { SimuladoService } from 'src/app/services/simulado.service';

@Component({
  selector: 'app-realizar-simulado',
  templateUrl: './realizar-simulado.component.html',
  styleUrls: ['./realizar-simulado.component.css']
})
export class RealizarSimuladoComponent implements OnInit{

  simulado:ISimuladoDTO;

  exibicaoPerguntas: boolean[] = []

  respostasMarcadas: IRespostaMarcada[]=[]

  perguntaAtual: number =0
  totalPerguntas: number = 0

  constructor(private simuladoService:SimuladoService,private route: ActivatedRoute, private toastr: ToastrService,private respUserService: RespostasUsuariosService,private router:Router ){
    this.simulado = {perguntas:[],materias:[],id:0,bancas:[]}
  }
  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap
    let id = parseInt(routeParams.get('id') || '')
    this.simuladoService.realizar(id).subscribe(
      {
        next:(sim)=>{
          this.simulado = sim
          sim.perguntas.forEach((element,i) => {
            this.exibicaoPerguntas.push(false)
            this.respostasMarcadas.push({idRespostaMarcada:null,numeroPergunta:i})
          });
          this.exibicaoPerguntas[0]=true
          this.totalPerguntas = sim.perguntas.length - 1;
        },
        error:(erro)=>this.toastr.error(erro.error.message)
      }
    )
  }

  alterarPergunta(pergunta:number){
    this.exibicaoPerguntas = this.exibicaoPerguntas.map(x=>false);
    this.exibicaoPerguntas[pergunta]=true
    this.perguntaAtual=pergunta
  }

  novaRespostaMarcada(novaResposta:IRespostaMarcada){
    let index: number = this.respostasMarcadas.findIndex(p=>p.numeroPergunta==novaResposta.numeroPergunta)

    if(index === -1){
      this.respostasMarcadas.push(novaResposta)

      return
    }
    this.respostasMarcadas[index] = novaResposta;
  }

  salvar(){
    const routeParams = this.route.snapshot.paramMap
    let id = parseInt(routeParams.get('id') || '')
    let respsParaEnvio : IGeneric[] = []
    this.respostasMarcadas.forEach((resp)=>{
      if(resp.idRespostaMarcada!=null){
        respsParaEnvio.push({id:resp.idRespostaMarcada})
      }
    })
    let respUser : IRespostaUsuario = {simulado:{id:id},usuario:{id:1},respostas:respsParaEnvio}
    this.respUserService.salvar(respUser).subscribe(
      {
        next:(resp)=>{
          this.toastr.success('Simulado finalizado com sucesso!!')
          this.router.navigate(['app','simulados','resultado',respUser.simulado.id,respUser.usuario.id])
      },
        error:(erro)=>{this.toastr.error(erro.error.message)}
      }
    )
  }


}
