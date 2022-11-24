import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import IUsuario from 'src/app/interfaces/IUsuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-edita-usuario',
  templateUrl: './edita-usuario.component.html',
  styleUrls: ['./edita-usuario.component.css']
})
export class EditaUsuarioComponent implements OnInit {

  usuario: IUsuario ={nome:"", email: "", tipo: "", senha:"" }

  constructor(private route: ActivatedRoute, private service:UsuarioService){ }
  
  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap
    let iduser = parseInt(routeParams.get('idusuario') || '')
    this.service.consultarbyId(iduser).subscribe(data => this.usuario = data)
  }

  alterar(){
    this.service.alterar(this.usuario).subscribe((data: any) => console.log(data))
  }

}
