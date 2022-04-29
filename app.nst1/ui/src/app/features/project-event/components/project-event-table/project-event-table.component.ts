import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ProjectEvent } from 'src/app/shared/models/projectEvent.model';
import { ProjectEventApiService } from 'src/app/shared/services/project-event-api.service';

@Component({
  selector: 'app-project-event-table',
  templateUrl: './project-event-table.component.html',
  styleUrls: ['./project-event-table.component.scss'],
})
export class ProjectEventTableComponent implements OnInit {
  @Output() detailsPorojectEvent: EventEmitter<ProjectEvent> =
    new EventEmitter();
  @Input() projectEventList: ProjectEvent[];

  constructor(private projectEventApi: ProjectEventApiService) {}

  ngOnInit(): void {}

  detailOfProjectRvent(project: ProjectEvent) {
    console.log('project -> ', project);
    this.detailsPorojectEvent.emit(project);
  }
}
