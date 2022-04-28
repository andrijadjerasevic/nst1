import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjectEventRoutingModule } from './project-event-routing.module';
import { ProjectEventHomeComponent } from './pages/project-event-home/project-event-home.component';
import { ProjectEventFormComponent } from './components/project-event-form/project-event-form.component';
import { ProjectEventTableComponent } from './components/project-event-table/project-event-table.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [
    ProjectEventHomeComponent,
    ProjectEventFormComponent,
    ProjectEventTableComponent,
  ],
  imports: [CommonModule, ProjectEventRoutingModule, SharedModule],
})
export class ProjectEventModule {}
