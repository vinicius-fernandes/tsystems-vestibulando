import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent } from './componentes/perguntas/listar/listar.component';


const routes: Routes = [
 {path:'perguntas',component:ListarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
