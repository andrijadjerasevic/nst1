import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-project-event-form',
  templateUrl: './project-event-form.component.html',
  styleUrls: ['./project-event-form.component.scss'],
})
export class ProjectEventFormComponent implements OnInit {
  projecEventForm: FormGroup;
  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm() {
    this.projecEventForm = this.fb.group({
      projectEventName: [
        null,
        // Validators.required,
        // Validators.minLength(3),
        // Validators.maxLength(100),
      ],
      projectEventLocation: [null],
      projectEventDescription: [null],
      startDate: [null],
      endDate: [null],
    });
  }
  save() {
    console.log(this.projecEventForm.value);
  }
}
