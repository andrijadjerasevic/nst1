import { Component, OnInit } from '@angular/core';
import { ProjectEvent } from 'src/app/model/projectEvent';
import { ProjectEventService } from 'src/app/service/projectEventService/project-event.service';

@Component({
  selector: 'app-project-event-list',
  templateUrl: './project-events-list.component.html',
  styleUrls: ['./project-events-list.component.css'],
})
export class ProjectEventsListComponent implements OnInit {
  projectEvents?: ProjectEvent[];

  currentProjectEvent: ProjectEvent = {};

  currentIndex = -1;
  title = '';

  constructor(private projectEventService: ProjectEventService) {}

  ngOnInit(): void {
    this.retrieveProjectEvents();
  }

  retrieveProjectEvents(): void {
    this.projectEventService.getAllProjectEvents().subscribe({
      next: (data) => {
        this.projectEvents = data;
        console.log(data);
      },
      error: (e) => console.error(e),
    });
  }

  refreshList(): void {
    this.retrieveProjectEvents();
    this.currentProjectEvent = {};
    this.currentIndex = -1;
  }

  setActiveProjectEvent(projectEvent: ProjectEvent, index: number): void {
    this.currentProjectEvent = projectEvent;
    this.currentIndex = index;
  }

  removeAllProjectEvents(): void {
    this.projectEventService.deleteAll().subscribe({
      next: (res) => {
        console.log(res);
        this.refreshList();
      },
      error: (e) => console.error(e),
    });
  }

  searchByNameProjectEvent(): void {
    this.currentProjectEvent = {};
    this.currentIndex = -1;

    this.projectEventService.findByTitleProjectEvent(this.title).subscribe({
      next: (data) => {
        this.projectEvents = data;
        console.log(data);
      },
      error: (e) => console.error(e),
    });
  }
}
