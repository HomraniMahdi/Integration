import { ComponentFixture, TestBed } from '@angular/core/testing';
import  {EtudiantNewComponent} from './etudiant-new.component'

describe('Etudiant', () => {
  let component: EtudiantNewComponent;
  let fixture: ComponentFixture<EtudiantNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EtudiantNewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EtudiantNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
