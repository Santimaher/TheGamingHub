import { CategoriaJ } from './../Modelo/CategoriaJ';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  constructor(private http: HttpClient) { }

  Url = 'http://localhost:8080';

  getCategorias(){
    return this.http.get<CategoriaJ[]>(this.Url + '/categoria/r');
  }
}
