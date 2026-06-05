import { TestBed } from '@angular/core/testing';

import { PrevesionService } from './prevesion-service';

describe('PrevesionService', () => {
  let service: PrevesionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrevesionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
