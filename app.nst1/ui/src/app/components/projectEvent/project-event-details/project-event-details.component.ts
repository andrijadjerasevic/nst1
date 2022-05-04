import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Admin } from 'src/app/model/admin';
import { ProjectEvent } from 'src/app/model/projectEvent';

@Component({
  selector: 'app-project-event-form',
  templateUrl: './project-event-details.component.html',
  styleUrls: ['./project-event-details.component.scss'],
})
export class ProjectEventDetailsComponent implements OnInit {
  _projectEvent: ProjectEvent;
  projecEventForm: FormGroup;

  @Input() set projectEvent(projectEvent: ProjectEvent) {
    this._projectEvent = projectEvent;
  }
  @Output() projectEventSaved: EventEmitter<ProjectEvent> = new EventEmitter();

  submited = false;
  model = new ProjectEvent(
    '18',
    'Dr IQ',
    'ds',
    '',
    new Date(),
    new Date(),
    new Admin('a', 'a')
  );

  constructor() {}

  ngOnInit(): void {}

  onSubmit() {
    this.submited = true;
  }
}
