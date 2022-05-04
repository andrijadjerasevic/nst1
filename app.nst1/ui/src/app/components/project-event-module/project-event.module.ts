import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjectEventRoutingModule } from './project-event-routing.module';
import { ProjectEventListComponent } from './project-events-list/project-event-list.component';
import { ProjectEventFormComponent } from './project-event-form/project-event-form.component';
import { ProjectEventHomeComponent } from './project-event-home/project-event-home.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    ProjectEventHomeComponent,
    ProjectEventFormComponent,
    ProjectEventListComponent,
  ],
  imports: [CommonModule, SharedModule, ProjectEventRoutingModule],
})
export class ProjectEventModule {}
