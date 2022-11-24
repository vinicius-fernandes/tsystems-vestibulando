import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GerenciaQuestoesComponent } from './componentes/questoes/gerencia-questoes/gerencia-questoes.component';
import { AdicionaQuestoesComponent } from './componentes/questoes/adiciona-questoes/adiciona-questoes.component';
import { EditaQuestoesComponent } from './componentes/questoes/edita-questoes/edita-questoes.component';
import { AdicionaBancaComponent } from './componentes/layout/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/layout/edita-banca/edita-banca.component';
import { GerenciaBancasComponent } from './componentes/layout/gerencia-bancas/gerencia-bancas.component';
import { GerenciaMateriasComponent } from './componentes/layout/gerencia-materias/gerencia-materias.component';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { GerarSimuladoComponent } from './componentes/simulado/gerar-simulado/gerar-simulado.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { RankingSimuladoComponent } from './componentes/ranking-simulado/ranking-simulado.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';
import { EditaUsuarioComponent } from './componentes/edita-usuario/edita-usuario.component';

const routes: Routes = [
  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      { path: 'questoes', component: GerenciaQuestoesComponent },
      { path: 'questoes/adiciona', component: AdicionaQuestoesComponent },
      { path: 'questoes/edita/:id', component: EditaQuestoesComponent },
      { path: 'gerarSimulado', component: GerarSimuladoComponent },
      { path: 'modbancas', component: GerenciaBancasComponent },
      { path: 'modbancas/addbanca', component: AdicionaBancaComponent },
      { path: 'modbancas/editbanca/:id', component: EditaBancaComponent },
      { path: 'modmaterias', component: GerenciaMateriasComponent },
      { path: 'rankingSimulado/:id', component: RankingSimuladoComponent },
      { path: 'usuarios', component: UsuariosComponent },
      { path: 'usuarios/edita/:idusuario', component: EditaUsuarioComponent }

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
