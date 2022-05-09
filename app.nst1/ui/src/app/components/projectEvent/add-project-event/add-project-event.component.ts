import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { ProjectEvent } from 'src/app/model/projectEvent';
import { ProjectEventService } from 'src/app/service/projectEventService/project-event.service';

@Component({
  selector: 'app-add-project-event',
  templateUrl: './add-project-event.component.html',
  styleUrls: ['./add-project-event.component.css'],
})
export class AddProjectEventComponent implements OnInit {
  DEFAULT_ADMIN: string = 'andrija.djerasevic@gmail.com';
  projectEvent: ProjectEvent = {};

  submitted = false;

  constructor(
    private projectEventService: ProjectEventService,
    private route: Router
  ) {}

  ngOnInit(): void {}

  saveProjectEvent(): void {
    const data = {
      projectEventName: this.projectEvent.projectEventName,
      projectEventLocation: this.projectEvent.projectEventLocation,
      projectEventDescription: this.projectEvent.projectEventDescription,
      startDate: this.projectEvent.startDate,
      endDate: this.projectEvent.endDate,
      admin: this.projectEvent.admin
        ? this.projectEvent.admin
        : this.DEFAULT_ADMIN,
    };

    this.projectEventService.saveProjectEvent(data).subscribe({
      next: (response) => {
        this.submitted = true;
        this.route.navigate(['/projectEvents']);
      },
      error: (e) => console.error(e),
    });
  }

  newProjectEvent(): void {
    this.projectEvent = {};
  }
}
