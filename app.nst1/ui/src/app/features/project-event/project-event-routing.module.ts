import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectEventHomeComponent } from './pages/project-event-home/project-event-home.component';

const routes: Routes = [
  {
    path: '',
    component: ProjectEventHomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProjectEventRoutingModule {}
