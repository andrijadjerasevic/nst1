import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { ProjectEventDetailsComponent } from './components/projectEvent/project-event-details/project-event-details.component';
import { ProjectEventListComponent } from './components/projectEvent/project-events-list/project-event-list.component';

// routes for  all components
const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'projectEvents', component: ProjectEventListComponent },
  { path: 'projectEventDetails', component: ProjectEventDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
