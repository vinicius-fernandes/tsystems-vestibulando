import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { RespostasComponent } from './componentes/respostas/respostas.component';

const routes: Routes = [
  {
    path: 'app',
    component: LayoutPadraoComponent,
    children: [
      {path: 'respostas', component: RespostasComponent},
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
