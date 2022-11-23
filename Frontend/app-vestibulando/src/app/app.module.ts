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
import { ListarComponent } from './componentes/perguntas/listar/listar.component';
import {HttpClientModule} from '@angular/common/http';
import { environment } from 'src/environment';
import { GerenciaMateriasComponent } from './componentes/layout/gerencia-materias/gerencia-materias.component';
import { GerenciaBancasComponent } from './componentes/layout/gerencia-bancas/gerencia-bancas.component';
import { AdicionaBancaComponent } from './componentes/layout/adiciona-banca/adiciona-banca.component';
import { EditaBancaComponent } from './componentes/layout/edita-banca/edita-banca.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    ListarComponent,
    GerenciaBancasComponent,
    GerenciaMateriasComponent,
    AdicionaBancaComponent,
    EditaBancaComponent
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
    ToastrModule.forRoot()
  ],
  providers: [ { provide: "BASE_API_URL", useValue: environment.apiUrl }],
  bootstrap: [AppComponent]
})
export class AppModule { }
