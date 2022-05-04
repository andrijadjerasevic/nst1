import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Admin } from 'src/app/model/admin';
import { ProjectEvent } from 'src/app/model/projectEvent';
import { ProjectEventService } from 'src/app/service/projectEventService/project-event.service';

@Component({
  selector: 'app-project-event-form',
  templateUrl: './project-event-form.component.html',
  styleUrls: ['./project-event-form.component.scss'],
})
export class ProjectEventFormComponent implements OnInit {
  @Input() set projectEvent(projectEvent: ProjectEvent) {
    this._projectEvent = projectEvent;
    this.buildForm(projectEvent);
  }

  @Output() savedProjectEvent: EventEmitter<ProjectEvent> = new EventEmitter();

  _projectEvent: ProjectEvent;
  projectEventForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private projectEventService: ProjectEventService
  ) {}

  ngOnInit(): void {
    // this.buildForm(this.projectEvent);
  }

  buildForm(projectEvent?: ProjectEvent) {
    this.projectEventForm = this.fb.group({
      projectEventName: [
        projectEvent ? projectEvent.projectEventName : null,
        [Validators.required],
      ],
      projectEventLocation: [
        projectEvent ? projectEvent.projectEventLocation : null,
        [Validators.required],
      ],
      projectEventDescription: [
        projectEvent ? projectEvent.projectEventDescription : null,
        [Validators.required],
      ],
      startDate: [
        projectEvent ? projectEvent.startDate : null,
        [Validators.required],
      ],
      endDate: [
        projectEvent ? projectEvent.endDate : null,
        [Validators.required],
      ],
    });
  }

  save(): void {
    console.log(this.projectEventForm.value);
    let projectEvent: ProjectEvent = this.projectEventForm.value;

    // todo fix this
    projectEvent.admin = new Admin('andrija.djerasevic@gmail.com');

    this.projectEventService
      .saveProjectEvent(projectEvent)
      .subscribe((response) => {
        this.savedProjectEvent.emit(response);
      });
  }

  onSubmit() {}
}
