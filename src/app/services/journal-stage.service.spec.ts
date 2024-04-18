import { TestBed } from '@angular/core/testing';

import { JournalStageService } from './journal-stage.service';

describe('JournalStageService', () => {
  let service: JournalStageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JournalStageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
