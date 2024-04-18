import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePost1Component } from './create-post1.component';

describe('CreatePost1Component', () => {
  let component: CreatePost1Component;
  let fixture: ComponentFixture<CreatePost1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePost1Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePost1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
