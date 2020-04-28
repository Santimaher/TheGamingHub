import { AddComponent } from './Categoria/add/add.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarComponent } from './Categoria/listar/listar.component';
import { EditComponent } from './Categoria/edit/edit.component';
import { DeleteComponent } from './Categoria/delete/delete.component';



const routes: Routes = [
  {path:'listarC', component:ListarComponent},
  {path:'addC', component:AddComponent},
  {path:'editC', component:EditComponent},
  {path:'deleteC', component:DeleteComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
