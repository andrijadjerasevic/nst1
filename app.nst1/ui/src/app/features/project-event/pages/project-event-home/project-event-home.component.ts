import { Component, OnInit } from '@angular/core';
import { ProjectEvent } from 'src/app/shared/models/projectEvent.model';
import { ProjectEventApiService } from 'src/app/shared/services/project-event-api.service';

@Component({
  selector: 'app-project-event-home',
  templateUrl: './project-event-home.component.html',
  styleUrls: ['./project-event-home.component.scss'],
})
export class ProjectEventHomeComponent implements OnInit {
  projectEventList: ProjectEvent[];

  selectedProjectEvent: ProjectEvent;

  constructor(private projectEventApiService: ProjectEventApiService) {}

  ngOnInit(): void {
    this.loadAllProjectEvents();
  }

  loadAllProjectEvents() {
    this.projectEventApiService.getAll().subscribe(
      (response) => {
        this.projectEventList = response;
        console.log('response -> ', response);
      },
      (error) => {
        console.error('error -> ', error);
      }
    );
  }

  detailOfProjectRvent(projectEvent: ProjectEvent) {
    console.log('ProjectEvent in Home componet -> ', projectEvent);
    this.selectedProjectEvent = projectEvent;
  }

  addNewProjectEvent(): void {
    this.selectedProjectEvent = {};
  }

  projectEventSaved(projectEvent: ProjectEvent) {
    this.loadAllProjectEvents();
  }
}
