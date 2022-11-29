import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdicionaBancaComponent } from './componentes/bancas/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/bancas/edita-banca/edita-banca.component';
import { GerenciaBancasComponent } from './componentes/bancas/gerencia-bancas/gerencia-bancas.component';
import { GerenciaMateriasComponent } from './componentes/materias/gerencia-materias/gerencia-materias.component';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { ListaSimuladosComponent } from "./componentes/lista-simulados/lista-simulados.component";
import { GerenciaQuestoesComponent } from './componentes/questoes/gerencia-questoes/gerencia-questoes.component';
import { AdicionaQuestoesComponent } from './componentes/questoes/adiciona-questoes/adiciona-questoes.component';
import { EditaQuestoesComponent } from './componentes/questoes/edita-questoes/edita-questoes.component';
import { GerarSimuladoComponent } from './componentes/simulado/gerar-simulado/gerar-simulado.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { RealizarSimuladoComponent } from './componentes/simulado/realizar-simulado/realizar-simulado.component';
import { ResultadoSimuladoComponent } from './componentes/simulado/resultado-simulado/resultado-simulado.component';
import { RankingSimuladoComponent } from './componentes/ranking-simulado/ranking-simulado.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';
import { EditaUsuarioComponent } from './componentes/edita-usuario/edita-usuario.component';
import { AddusuarioComponent } from './componentes/addusuario/addusuario.component';
import { AdmGuard } from './authGuard/AdmGuard';
import { ValidUserGuard } from './authGuard/ValidUserGuard';

import { AdicionaMateriaComponent } from './componentes/materias/adiciona-materia/adiciona-materia.component';
import { EditaMateriaComponent } from './componentes/materias/edita-materia/edita-materia.component';
import { HomeComponent } from './componentes/home/home.component';
import { InfoComponent } from './componentes/info/info.component';
import { RankingGeralComponent } from './componentes/ranking-geral/ranking-geral.component';
const routes: Routes = [
  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      { path: 'simulados/gerarSimulado',component:GerarSimuladoComponent },
      { path: 'simulados/realizar/:id',component:RealizarSimuladoComponent },
      { path: 'simulados/resultado/:idSimulado/:idUser',component:ResultadoSimuladoComponent },
      { path: 'simulados', component: ListaSimuladosComponent },
      { path: 'simulados/rankingSimulado/:id', component:RankingSimuladoComponent },
      { path: 'modbancas', component: GerenciaBancasComponent, canActivate:[AdmGuard] },
      { path: 'modbancas/addbanca', component: AdicionaBancaComponent, canActivate:[AdmGuard] },
      { path: 'modbancas/editbanca/:id', component: EditaBancaComponent, canActivate:[AdmGuard]},
      { path: 'modmaterias', component: GerenciaMateriasComponent , canActivate:[AdmGuard]},
      { path: 'modmaterias/addmateria', component: AdicionaMateriaComponent, canActivate:[AdmGuard]},
      { path: 'modmaterias/editmateria/:id', component: EditaMateriaComponent, canActivate:[AdmGuard]},
      { path: 'questoes', component: GerenciaQuestoesComponent , canActivate:[AdmGuard]},
      { path: 'questoes/adiciona', component: AdicionaQuestoesComponent , canActivate:[AdmGuard]},
      { path: 'questoes/edita/:id', component: EditaQuestoesComponent, canActivate:[AdmGuard] },
      { path: 'gerarSimulado', component: GerarSimuladoComponent },
      { path: 'rankingSimulado/:id', component: RankingSimuladoComponent },
      { path: 'usuarios', component: UsuariosComponent , canActivate:[AdmGuard]},
      { path: 'usuarios/edita/:idusuario', component: EditaUsuarioComponent , canActivate:[AdmGuard]},
      { path: 'usuarios/adduser', component: AddusuarioComponent, canActivate:[AdmGuard] },
      { path: 'home', component: HomeComponent },
      {path:'info/:mensagem',component:InfoComponent},
      {path:'ranking',component:RankingGeralComponent}
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
