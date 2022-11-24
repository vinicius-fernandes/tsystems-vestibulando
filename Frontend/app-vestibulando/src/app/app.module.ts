import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatChipsModule } from '@angular/material/chips';

import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/layout/header/header.component';
import { SidebarComponent } from './componentes/layout/sidebar/sidebar.component';
import { FooterComponent } from './componentes/layout/footer/footer.component';
import { AppRoutingModule } from './app-routing.module';
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
import { RankingSimuladoComponent } from './componentes/ranking-simulado/ranking-simulado.component';
import { RealizarSimuladoComponent } from './componentes/simulado/realizar-simulado/realizar-simulado.component';
import { ListaSimuladosComponent } from './componentes/lista-simulados/lista-simulados.component';
import { PerguntaSimuladoComponent } from './componentes/simulado/pergunta-simulado/pergunta-simulado.component';
import { SumarioSimuladoComponent } from './componentes/simulado/sumario-simulado/sumario-simulado.component';
import { ResultadoSimuladoComponent } from './componentes/simulado/resultado-simulado/resultado-simulado.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';
import { EditaUsuarioComponent } from './componentes/edita-usuario/edita-usuario.component';
import { AdicionaMateriaComponent } from './componentes/materias/adiciona-materia/adiciona-materia.component';
import { EditaMateriaComponent } from './componentes/materias/edita-materia/edita-materia.component';
import { AddusuarioComponent } from './componentes/addusuario/addusuario.component';
import { GerenciaQuestoesComponent } from './componentes/questoes/gerencia-questoes/gerencia-questoes.component';
import { EditaQuestoesComponent } from './componentes/questoes/edita-questoes/edita-questoes.component';
import { AdicionaQuestoesComponent } from './componentes/questoes/adiciona-questoes/adiciona-questoes.component';

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
    RealizarSimuladoComponent,
    ListaSimuladosComponent,
    PerguntaSimuladoComponent,
    SumarioSimuladoComponent,
    ResultadoSimuladoComponent,
    UsuariosComponent,
    EditaUsuarioComponent,
    AdicionaMateriaComponent,
    EditaMateriaComponent,
    AddusuarioComponent,
    GerenciaQuestoesComponent,
    EditaQuestoesComponent,
    AdicionaQuestoesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
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
    MatButtonModule,
    MatRadioModule,
    MatChipsModule
    ],
  providers: [ { provide: "BASE_API_URL", useValue: environment.apiUrl }],
  bootstrap: [AppComponent]
})
export class AppModule { }
