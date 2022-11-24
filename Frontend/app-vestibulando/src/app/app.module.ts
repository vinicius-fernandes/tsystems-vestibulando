import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/layout/header/header.component';
import { SidebarComponent } from './componentes/layout/sidebar/sidebar.component';
import { FooterComponent } from './componentes/layout/footer/footer.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import { environment } from 'src/environment';
import { GerenciaMateriasComponent } from './componentes/materias/gerencia-materias/gerencia-materias.component';
import { GerenciaBancasComponent } from './componentes/bancas/gerencia-bancas/gerencia-bancas.component';
import { AdicionaBancaComponent } from './componentes/bancas/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/bancas/edita-banca/edita-banca.component';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { GerarSimuladoComponent } from './componentes/simulado/gerar-simulado/gerar-simulado.component';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { RankingSimuladoComponent } from './componentes/ranking-simulado/ranking-simulado.component';
import { PerguntasComponent } from './componentes/perguntas/perguntas.component';
import { RespostasComponent } from './componentes/respostas/respostas.component';
import { AdicionaMateriaComponent } from './componentes/materias/adiciona-materia/adiciona-materia.component';
import { EditaMateriaComponent } from './componentes/materias/edita-materia/edita-materia.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    GerenciaBancasComponent,
    GerenciaMateriasComponent,
    AdicionaBancaComponent,
    EditaBancaComponent,
    LoginComponent,
    CadastroComponent,
    TelaComponent,
    LayoutPadraoComponent,
    GerarSimuladoComponent,
    RankingSimuladoComponent,
    PerguntasComponent,
    RespostasComponent,
    AdicionaMateriaComponent,
    EditaMateriaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
    ],
  providers: [ { provide: "BASE_API_URL", useValue: environment.apiUrl }],
  bootstrap: [AppComponent]
})
export class AppModule { }
