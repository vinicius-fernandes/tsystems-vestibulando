import { Component } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private _router: Router) { }

  redirecionarCadastro() {
    this._router.navigateByUrl('/cadastro')
  }

  redirecionarGerarSimulado() {
    this._router.navigateByUrl('/app/gerarSimulado')
  }
}
