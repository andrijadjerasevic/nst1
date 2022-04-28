import { Component, OnInit } from '@angular/core';
import { ProjectEvent } from 'src/app/shared/models/projectEvent.model';
import { ProjectEventApiService } from 'src/app/shared/services/project-event-api.service';

@Component({
  selector: 'app-project-event-table',
  templateUrl: './project-event-table.component.html',
  styleUrls: ['./project-event-table.component.scss'],
})
export class ProjectEventTableComponent implements OnInit {
  projectEventList: ProjectEvent[] = [];
  constructor(private projectEventApi: ProjectEventApiService) {}

  ngOnInit(): void {
    this.loadAllProjectEvent();
  }

  loadAllProjectEvent() {
    // todo returns array of objects instead array od projectEvents
    this.projectEventApi.getAll().subscribe(
      (response) => {
        this.projectEventList = response;
        console.log('response -> ', response);
      },
      (error) => {
        console.error('error -> ', error);
      }
    );
  }

  detailOfProjectRvent(project: ProjectEvent) {
    console.log(project);
  }
}
