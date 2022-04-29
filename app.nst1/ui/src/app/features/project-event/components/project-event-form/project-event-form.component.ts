import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProjectEvent } from 'src/app/shared/models/projectEvent.model';
import { ProjectEventApiService } from 'src/app/shared/services/project-event-api.service';

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

  @Output() projectEventSaved: EventEmitter<ProjectEvent> = new EventEmitter();

  _projectEvent: ProjectEvent;
  projecEventForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private projectEventApiService: ProjectEventApiService
  ) {}

  ngOnInit(): void {
    // if there is setter, this is not needed
    // this.buildForm();
  }

  buildForm(projectEvent?: ProjectEvent) {
    this.projecEventForm = this.fb.group({
      projectEventName: [
        projectEvent ? projectEvent.projectEventName : null,
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(100),
      ],
      projectEventLocation: [
        projectEvent ? projectEvent.projectEventLocation : null,
        Validators.required,
        Validators.minLength(3),
        Validators.minLength(100),
      ],
      projectEventDescription: [
        projectEvent ? projectEvent.projectEventDescription : null,
        Validators.required,
        Validators.minLength(3),
        Validators.minLength(100),
      ],
      startDate: [projectEvent ? projectEvent.startDate : null],
      endDate: [projectEvent ? projectEvent.endDate : null],
    });
  }
  save() {
    console.log(this.projecEventForm.value);
    this.projectEventApiService
      .saveProjectEvent(this.projecEventForm.value)
      .subscribe((response) => {
        console.log('Svaed projectEvent -> ', response);
        this.projectEventSaved.emit(response);
      });
  }
}
