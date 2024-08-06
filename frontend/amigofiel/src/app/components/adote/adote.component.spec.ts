import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdoteComponent } from './adote.component';

describe('AdoteComponent', () => {
  let component: AdoteComponent;
  let fixture: ComponentFixture<AdoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdoteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
