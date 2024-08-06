// src/app/animal-list/animal-list.component.ts
import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../service/animal.service';
import { Animal } from '../model/animal';

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {
  animais: Animal[] = [];

  constructor(private animalService: AnimalService) { }

  ngOnInit(): void {
    this.animalService.findAllAnimals().subscribe(
      (data: Animal[]) => {
        this.animais = data;
      },
      (error) => {
        console.error('Erro ao buscar animais:', error);
      }
    );
  }
}
