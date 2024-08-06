import { Address } from './address';
import { CurrentStatus } from './enums/current-status.enum';
import { Size } from './enums/size.enum';
import { Specie } from './enums/specie.enum';

export class Animal {
    id: number = 0;
    name: string = '';
    specie!: Specie;
    breed: string = '';
    birthDate!: Date;
    sex: string = '';
    weight: number = 0;
    size!: Size;
    neutered: boolean = false;
    address!: Address;
    registrationDate!: Date;
    currentStatus!: CurrentStatus;
    adoption?: null;
}