import { Component, OnInit } from '@angular/core';
import { AnimalService } from '../service/animal.service';
import { Animal } from '../model/animal';

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {
  animals: Animal[] = [];

  constructor(private animalService: AnimalService) { }

  ngOnInit(): void {
    this.getAnimals();
  }

  getAnimals(): void {
    this.animalService.findAllAnimals().subscribe(
      data => {
        this.animals = data;
      },
      error => {
        console.error('There was an error!', error);
      }
    );
  }

  deleteAnimal(id: number): void {
    this.animalService.deleteAnimalById(id).subscribe(
      response => {
        console.log(response);
        this.getAnimals(); // Refresh the list after deletion
      },
      error => {
        console.error('There was an error!', error);
      }
    );
  }
}

