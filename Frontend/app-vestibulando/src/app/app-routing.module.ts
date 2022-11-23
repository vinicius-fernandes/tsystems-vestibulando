import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdicionaBancaComponent } from './componentes/layout/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/layout/edita-banca/edita-banca.component';
import { GerenciaBancasComponent } from './componentes/layout/gerencia-bancas/gerencia-bancas.component';
import { GerenciaMateriasComponent } from './componentes/layout/gerencia-materias/gerencia-materias.component';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { GerarSimuladoComponent } from './componentes/simulado/gerar-simulado/gerar-simulado.component';

import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import {RankingSimuladoComponent} from "./componentes/ranking-simulado/ranking-simulado.component";
import { RespostasComponent } from './componentes/respostas/respostas.component';
const routes: Routes = [
  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      {path:'gerarSimulado',component:GerarSimuladoComponent},
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
      {
        path: 'respostas', component: RespostasComponent
      },
      {
        path: 'rankingSimulado/:id', component:RankingSimuladoComponent
      }
    ]
  },
 {path:'', component:TelaComponent},
 {path:'login', component:LoginComponent},
 {path:'cadastro', component:CadastroComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
