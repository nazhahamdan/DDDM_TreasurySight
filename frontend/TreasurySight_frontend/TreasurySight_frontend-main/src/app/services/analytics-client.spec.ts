import { TestBed } from '@angular/core/testing';

import { AnalyticsClientService } from './analytics-client';

describe('AnalyticsClientService', () => {
  let service: AnalyticsClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnalyticsClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
