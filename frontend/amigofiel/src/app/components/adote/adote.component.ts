import { Component } from '@angular/core';

@Component({
  selector: 'app-adote',
  templateUrl: './adote.component.html',
  styleUrl: './adote.component.css'
})
export class AdoteComponent {

  tranparencia:boolean = false
  ShowDadosPets:boolean = false

  imgPets = [
    "CatAdult.png",
    "CatAdult2.png",
    "CatAdult3.png",
    "CatBaby.png",
    "DogAdult.png",
    "DogBaby.png",
    "DogBeagleAdult.png"
  ]

  AbrirDados(){
    this.tranparencia = true
    this.ShowDadosPets = true
  }

  FecharDados(){
    this.tranparencia = false
    this.ShowDadosPets = false
  }

}
