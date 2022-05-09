import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { timeout } from 'rxjs';
import { ProjectEvent } from 'src/app/model/projectEvent';
import { ProjectEventService } from 'src/app/service/projectEventService/project-event.service';

@Component({
  selector: 'app-project-event-details',
  templateUrl: './project-event-details.component.html',
  styleUrls: ['./project-event-details.component.css'],
})
export class ProjectEventDetailsComponent implements OnInit {
  DEFAULT_ADMIN: string = 'andrija.djerasevic@gmail.com';
  @Input() viewMode = false;

  @Input() currentProjectEvent: ProjectEvent = {
    projectEventName: '',
    projectEventLocation: '',
    projectEventDescription: '',
  };

  message = {};

  constructor(
    private projectEventService: ProjectEventService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getProjectEvent(this.route.snapshot.params['id']);
    }
  }

  getProjectEvent(id: string): void {
    this.projectEventService.getProjectEvent(id).subscribe({
      next: (data) => {
        this.currentProjectEvent = data;
        // sets date in input
        this.currentProjectEvent.startDate =
          this.currentProjectEvent.startDate?.slice(0, 16);
        this.currentProjectEvent.endDate =
          this.currentProjectEvent.startDate?.slice(0, 16);
      },
      error: (e) => console.error(e),
    });
  }

  updateProjectEvent(): void {
    this.message = '';

    this.projectEventService
      .updateProjectEvent(this.currentProjectEvent)
      .subscribe({
        next: (response) => {
          this.message = 'This Project Event was updated successfully!';
          setTimeout(() => {
            this.router.navigate(['/projectEvents']);
          }, 1000);
        },
        error: (e) => console.error(e),
      });
  }

  deleteProjectEvent(id: string): void {
    this.projectEventService.deleteProjectEvent(id).subscribe(
      (response) => {
        console.debug(response);
        this.message = response;
        setTimeout(() => {
          this.router.navigate(['/projectEvents']);
        }, 1000);
      },
      (error) => {
        console.error(error.message);
      }
    );
  }
}
