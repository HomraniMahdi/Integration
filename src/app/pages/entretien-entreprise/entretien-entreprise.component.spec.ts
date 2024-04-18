import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntretienEntrepriseComponent } from './entretien-entreprise.component';

describe('EntretienEntrepriseComponent', () => {
  let component: EntretienEntrepriseComponent;
  let fixture: ComponentFixture<EntretienEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntretienEntrepriseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntretienEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
