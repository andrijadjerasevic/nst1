import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ProjectEvent } from '../model/projectEvent';
import { ProjectEventService } from '../service/project-event.service';

@Component({
  selector: 'app-project-events',
  templateUrl: './project-events.component.html',
  styleUrls: ['./project-events.component.scss'],
})
export class ProjectEventsComponent implements OnInit {
  @Output() detailsPorojectEvent: EventEmitter<ProjectEvent> =
    new EventEmitter();
  @Input() projectEvents: ProjectEvent[] = [];

  constructor(private projectEventService: ProjectEventService) {}

  ngOnInit(): void {
    this.getAllProjectEvents();
  }

  getAllProjectEvents(): void {
    this.projectEventService.getAllProjectEvents().subscribe((response) => {
      this.projectEvents = response;
    });
  }

  detailOfProjectRvent(project: ProjectEvent): void {
    console.log(project);
    this.detailsPorojectEvent.emit(project);
  }
}
