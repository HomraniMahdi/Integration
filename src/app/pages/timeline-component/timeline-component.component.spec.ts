import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TimelineComponent} from './timeline-component.component';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DemandeStageComponent } from '../demande-stage/demande-stage.component';

describe('TimelineComponentComponent', () => {
  let component: TimelineComponent;
  let fixture: ComponentFixture<TimelineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimelineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
@Component({
  selector: 'app-timeline',
  templateUrl: './timeline-component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent {
  constructor(private dialog: MatDialog) { }

  openDemandeStagePopup() {
    const dialogRef = this.dialog.open(DemandeStageComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
