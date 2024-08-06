import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AdoteComponent } from './components/adote/adote.component';
import { ContatoComponent } from './components/contato/contato.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "adote", component: AdoteComponent},
  {path: "contato", component: ContatoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
