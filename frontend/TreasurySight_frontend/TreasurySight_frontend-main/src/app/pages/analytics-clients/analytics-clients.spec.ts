import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsClients } from './analytics-clients';

describe('AnalyticsClients', () => {
  let component: AnalyticsClients;
  let fixture: ComponentFixture<AnalyticsClients>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalyticsClients]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalyticsClients);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
