import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { RespostasComponent } from './componentes/respostas/respostas.component';
import { PerguntasComponent } from './componentes/perguntas/perguntas.component';
import { ListarPerguntasComponent } from './componentes/listar-perguntas/listar-perguntas.component';
import { EditarRespostasComponent } from './componentes/editar-respostas/editar-respostas.component';

const routes: Routes = [
  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      {path: 'respostas', component: RespostasComponent},
      {path: 'perguntas', component: PerguntasComponent},
      {path: 'listarPerguntas', component: ListarPerguntasComponent},
      {path: 'editarRespostas', component: EditarRespostasComponent}
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
