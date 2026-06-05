import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MacroCard } from './macro-card';

describe('MacroCard', () => {
  let component: MacroCard;
  let fixture: ComponentFixture<MacroCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MacroCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MacroCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
