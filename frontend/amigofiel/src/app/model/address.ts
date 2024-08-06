export class Address {
    id: number = 0;
    street: string = '';
    city: string = '';
    state: string = '';
    zipCode: string = '';
  
    constructor(init?: Partial<Address>) {
      Object.assign(this, init);
    }
}