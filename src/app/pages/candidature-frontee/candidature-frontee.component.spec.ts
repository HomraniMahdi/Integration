import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatureFronteeComponent } from './candidature-frontee.component';

describe('CandidatureFronteeComponent', () => {
  let component: CandidatureFronteeComponent;
  let fixture: ComponentFixture<CandidatureFronteeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatureFronteeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandidatureFronteeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
