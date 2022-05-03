import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectEventsComponent } from './core/project-events/project-events.component';

// routes for  all components
const routes: Routes = [
  { path: 'projectEvents', component: ProjectEventsComponent },
  // { path: '', redirectTo: '/projectEvents', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
