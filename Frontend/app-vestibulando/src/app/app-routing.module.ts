import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { ListarComponent } from './componentes/perguntas/listar/listar.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import {RankingSimuladoComponent} from "./componentes/ranking-simulado/ranking-simulado.component";


const routes: Routes = [

  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      {path:'perguntas',component:ListarComponent},
      {path: 'rankingSimulado', component:RankingSimuladoComponent}
    ]
  },
 {path:'', component:TelaComponent},
 {path:'login', component:LoginComponent},
 {path:'cadastro', component:CadastroComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
