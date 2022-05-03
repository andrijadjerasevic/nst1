import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenubarModule } from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { ProjectEventsComponent } from './core/project-events/project-events.component';
import { MenuBarComponent } from './core/menu-bar/menu-bar.component';
import { ProjectEventFormComponent } from './core/project-event-form/project-event-form.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MenubarModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  declarations: [AppComponent, DashboardComponent, ProjectEventsComponent, MenuBarComponent, ProjectEventFormComponent],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
