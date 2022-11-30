import { Component } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private _router: Router) { }

  redirecionarGerarSimualdo() {
    this._router.navigateByUrl("app/simulados/gerarSimulado")
  }
  redirecionarSimualdos() {
    this._router.navigateByUrl("app/simulados")
  }
  redirecionarRanking() {
    this._router.navigateByUrl("app/ranking")
  }
}
