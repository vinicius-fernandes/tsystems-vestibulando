import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  msg: string = ''
  constructor(private route: ActivatedRoute) {

  }
  ngOnInit(): void {
    var routeParams = this.route.snapshot.paramMap
    this.msg = routeParams.get('mensagem') || ''
  }


}
