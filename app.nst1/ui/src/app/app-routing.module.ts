import { Host, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home/home.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';

// routes for  all components
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent },
  { path: 'home', component: HomeComponent },
  {
    path: 'projectEvent',
    loadChildren: () =>
      import('./features/project-event/project-event.module').then(
        (projectEvent) => projectEvent.ProjectEventModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
