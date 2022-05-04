import { Component, OnInit } from '@angular/core';
import { ProjectEvent } from 'src/app/model/projectEvent';
import { ProjectEventService } from 'src/app/service/projectEventService/project-event.service';

@Component({
  selector: 'app-project-event-home',
  templateUrl: './project-event-home.component.html',
  styleUrls: ['./project-event-home.component.scss'],
})
export class ProjectEventHomeComponent implements OnInit {
  selectedProjectEvent: ProjectEvent;
  projectEvents: ProjectEvent[] = [];

  constructor(private projectEventService: ProjectEventService) {}

  ngOnInit(): void {
    this.getAllProjectEvents();
  }

  detailsOfProjectEvent(projectEvent: ProjectEvent) {
    this.selectedProjectEvent = projectEvent;
  }
  addNewProjectEvent() {
    this.selectedProjectEvent = {};
  }

  getAllProjectEvents(): void {
    this.projectEventService.getAllProjectEvents().subscribe(
      (response) => {
        this.projectEvents = response;
      },
      (error) => {
        console.error('ERROR -> ', error);
      }
    );
  }

  savedProjectEvent(projectEvent: ProjectEvent) {
    // const foundProjectEvent = this.projectEvents.find(
    //   (p) => p.projectEventId === projectEvent.projectEventId
    // );
    // if (foundProjectEvent) {
    //   this.projectEvents.push(projectEvent);
    // } else {
    //   console.error('ERROR');
    // }
    this.getAllProjectEvents();
  }
}
