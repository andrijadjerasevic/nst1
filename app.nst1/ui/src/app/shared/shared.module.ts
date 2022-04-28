import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { MenubarModule } from 'primeng/menubar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ProjectEventPipe } from './pipes/project-event.pipe';

@NgModule({
  declarations: [
    ProjectEventPipe
  ],
  imports: [
    CommonModule,
    // BrowserModule,
    MenubarModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  exports: [
    CommonModule,
    // BrowserModule,
    MenubarModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ProjectEventPipe
  ],
})
export class SharedModule {}
