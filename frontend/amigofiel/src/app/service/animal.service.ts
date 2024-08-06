import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Animal } from '../model/animal';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {
  private apiUrl = 'http://localhost:8080/animals'; // URL do backend

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  createAnimal(animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(`${this.apiUrl}/create`, animal, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  findAnimalById(id: number): Observable<Animal> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Animal>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  findAllAnimals(): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${this.apiUrl}/all`)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateAnimal(id: number, animal: Animal): Observable<Animal> {
    const url = `${this.apiUrl}/update/${id}`;
    return this.http.put<Animal>(url, animal, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteAnimalById(id: number): Observable<string> {
    const url = `${this.apiUrl}/delete/${id}`;
    return this.http.delete<string>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError(error);
  }
}
