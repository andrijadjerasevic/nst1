import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ProjectEvent } from 'src/app/model/projectEvent';
import { ProjectEventService } from 'src/app/service/projectEventService/project-event.service';

@Component({
  selector: 'app-project-events',
  templateUrl: './project-event-list.component.html',
  styleUrls: ['./project-event-list.component.scss'],
})
export class ProjectEventListComponent implements OnInit {
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
