import { TestBed } from '@angular/core/testing';

import { EntretientEntrepriseService } from './entretient-entreprise.service';

describe('EntretientEntrepriseService', () => {
  let service: EntretientEntrepriseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntretientEntrepriseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
