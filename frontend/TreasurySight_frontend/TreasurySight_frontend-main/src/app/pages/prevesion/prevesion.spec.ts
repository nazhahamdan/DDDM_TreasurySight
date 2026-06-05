import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Prevesion } from './prevesion';

describe('Prevesion', () => {
  let component: Prevesion;
  let fixture: ComponentFixture<Prevesion>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Prevesion]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Prevesion);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
