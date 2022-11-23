import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/layout/header/header.component';
import { SidebarComponent } from './componentes/layout/sidebar/sidebar.component';
import { FooterComponent } from './componentes/layout/footer/footer.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import { environment } from 'src/environment';
import { LoginComponent } from './componentes/inicio/login/login.component';
import { CadastroComponent } from './componentes/inicio/cadastro/cadastro.component';
import { TelaComponent } from './componentes/inicio/tela/tela.component';
import { LayoutPadraoComponent } from './pages/layout-padrao/layout-padrao.component';
import { PerguntasComponent } from './componentes/perguntas/perguntas.component';
import { RespostasComponent } from './componentes/respostas/respostas.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    LoginComponent,
    CadastroComponent,
    TelaComponent,
    LayoutPadraoComponent,
    PerguntasComponent,
    RespostasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ { provide: "BASE_API_URL", useValue: environment.apiUrl }],
  bootstrap: [AppComponent]
})
export class AppModule { }
