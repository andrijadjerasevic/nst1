import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { AddProjectEventComponent } from './components/projectEvent/add-project-event/add-project-event.component';
import { ProjectEventDetailsComponent } from './components/projectEvent/project-event-details/project-event-details.component';
import { ProjectEventsListComponent } from './components/projectEvent/project-events-list/project-events-list.component';

// routes for  all components
const routes: Routes = [
  // { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'projectEvents', component: ProjectEventsListComponent },
  { path: 'projectEvent/:id', component: ProjectEventDetailsComponent },
  { path: 'add', component: AddProjectEventComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
