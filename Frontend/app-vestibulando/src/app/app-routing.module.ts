import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent } from './componentes/perguntas/listar/listar.component';
import { RespostasComponent } from './componentes/respostas/respostas.component';

const routes: Routes = [
  { path: 'perguntas', component: ListarComponent },
  {
    path: 'respostas',
    component: RespostasComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
