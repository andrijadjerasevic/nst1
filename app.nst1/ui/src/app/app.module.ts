import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddProjectEventComponent } from './components/projectEvent/add-project-event/add-project-event.component';
import { ProjectEventDetailsComponent } from './components/projectEvent/project-event-details/project-event-details.component';
import { ProjectEventsListComponent } from './components/projectEvent/project-events-list/project-events-list.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MaterialExampleModule } from 'src/material.module';
import { NgxMatTimepickerModule } from 'ngx-mat-timepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent,
    AddProjectEventComponent,
    ProjectEventDetailsComponent,
    ProjectEventsListComponent,
    LoginPageComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MatNativeDateModule,
    MaterialExampleModule,
    NgxMatTimepickerModule,
    MatInputModule,
    MatFormFieldModule,
  ],
  providers: [],
  bootstrap: [AppComponent], // which component shows first
})
export class AppModule {}
