import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdicionaBancaComponent } from './componentes/bancas/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/bancas/edita-banca/edita-banca.component';
import { GerenciaBancasComponent } from './componentes/bancas/gerencia-bancas/gerencia-bancas.component';
import { GerenciaMateriasComponent } from './componentes/materias/gerencia-materias/gerencia-materias.component';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { RespostasComponent } from './componentes/respostas/respostas.component';

import {ListaSimuladosComponent} from "./componentes/lista-simulados/lista-simulados.component";
import { PerguntasComponent } from './componentes/perguntas/perguntas.component';
import { ListarPerguntasComponent } from './componentes/listar-perguntas/listar-perguntas.component';
import { EditarRespostasComponent } from './componentes/editar-respostas/editar-respostas.component';
import { GerarSimuladoComponent } from './componentes/simulado/gerar-simulado/gerar-simulado.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { RealizarSimuladoComponent } from './componentes/simulado/realizar-simulado/realizar-simulado.component';
import { ResultadoSimuladoComponent } from './componentes/simulado/resultado-simulado/resultado-simulado.component';
import { RankingSimuladoComponent } from './componentes/ranking-simulado/ranking-simulado.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';
import { EditaUsuarioComponent } from './componentes/edita-usuario/edita-usuario.component';
import { AddusuarioComponent } from './componentes/addusuario/addusuario.component';


import { AdicionaMateriaComponent } from './componentes/materias/adiciona-materia/adiciona-materia.component';
import { EditaMateriaComponent } from './componentes/materias/edita-materia/edita-materia.component';
const routes: Routes = [
  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      {path:'simulados/gerarSimulado',component:GerarSimuladoComponent},
      {path:'simulados/realizar/:id',component:RealizarSimuladoComponent},
      {path:'simulados/resultado/:idSimulado/:idUser',component:ResultadoSimuladoComponent},
      { path: 'simulados', component: ListaSimuladosComponent },
      { path: 'simulados/rankingSimulado/:id', component:RankingSimuladoComponent},

      {
        path: 'modbancas', component: GerenciaBancasComponent
       },
       {
        path: 'modbancas/addbanca', component: AdicionaBancaComponent
       },
       {
        path: 'modbancas/editbanca/:id', component: EditaBancaComponent
       },
       {
        path: 'modmaterias', component: GerenciaMateriasComponent
       },



      { path: 'respostas', component: RespostasComponent },
      { path: 'perguntas', component: PerguntasComponent },
      { path: 'listarPerguntas', component: ListarPerguntasComponent },
      { path: 'editarRespostas', component: EditarRespostasComponent },
      { path: 'modmaterias/addmateria', component: AdicionaMateriaComponent},
      { path: 'modmaterias/editmateria/:id', component: EditaMateriaComponent},
      { path: 'respostas', component: RespostasComponent },
      { path: 'usuarios', component: UsuariosComponent },
      { path: 'usuarios/edita/:idusuario', component: EditaUsuarioComponent },
      { path: 'usuarios/adduser', component: AddusuarioComponent },
    ],
  },
  { path: '', component: TelaComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cadastro', component: CadastroComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
