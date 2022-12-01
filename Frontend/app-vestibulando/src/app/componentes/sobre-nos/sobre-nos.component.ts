import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sobre-nos',
  templateUrl: './sobre-nos.component.html',
  styleUrls: ['./sobre-nos.component.css']
})
export class SobreNosComponent implements OnInit{

  constructor(private _router: Router) {
    
  }
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


  redirecionarSobreNos() {
    window.scrollTo(0, 0);
  }



}
