import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-project-event-home',
  templateUrl: './project-event-home.component.html',
  styleUrls: ['./project-event-home.component.scss'],
})
export class ProjectEventHomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  addNewProjectEvent(): void {
    console.log('Add new ProjectEvent');
  }
}
