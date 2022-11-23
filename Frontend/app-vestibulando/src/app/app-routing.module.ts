import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdicionaBancaComponent } from './componentes/layout/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/layout/edita-banca/edita-banca.component';
import { GerenciaBancasComponent } from './componentes/layout/gerencia-bancas/gerencia-bancas.component';
import { GerenciaMateriasComponent } from './componentes/layout/gerencia-materias/gerencia-materias.component';
import { ListarComponent } from './componentes/perguntas/listar/listar.component';


const routes: Routes = [
 {path:'perguntas',component:ListarComponent},
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
 }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
