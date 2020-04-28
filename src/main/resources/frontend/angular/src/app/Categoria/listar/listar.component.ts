import { Component, OnInit } from '@angular/core';
import {ServiceService} from '../../Service/service.service';
import { CategoriaJ } from 'src/app/Modelo/CategoriaJ';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarComponent implements OnInit {

  categorias: CategoriaJ[];
  constructor(private service: ServiceService, private router: Router) { }

  ngOnInit(){
    this.service.getCategorias()
    .subscribe(data => {
      this.categorias = data;
    });
  }

}
