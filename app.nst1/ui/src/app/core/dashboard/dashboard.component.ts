import { Component, OnInit } from '@angular/core';
import { ProjectEvent } from '../model/projectEvent';
import { ProjectEventService } from '../service/project-event.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
