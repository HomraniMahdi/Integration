import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormModifyCandComponent } from './form-modify-cand.component';

describe('FormModifyCandComponent', () => {
  let component: FormModifyCandComponent;
  let fixture: ComponentFixture<FormModifyCandComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormModifyCandComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormModifyCandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
