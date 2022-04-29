import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HomeComponent } from './pages/home/home/home.component';
import { MainMenuComponent } from './core/components/main-menu/main-menu.component';
import { Menubar, MenubarModule } from 'primeng/menubar';
import { SharedModule } from 'primeng/api';
import { CoreModule } from './core/core.module';

@NgModule({
  declarations: [AppComponent, LoginPageComponent, HomeComponent],
  imports: [
    BrowserModule,
    SharedModule,
    AppRoutingModule,
    CoreModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
