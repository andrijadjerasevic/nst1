import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.scss'],
})
export class MenuBarComponent implements OnInit {
  items: MenuItem[];
  constructor() {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Project Event',
        items: [
          {
            label: 'New Project Event',
            icon: 'pi pi-fw pi-plus',
          },
          { label: 'Show All Project Events', url: '/projectEvent' },
        ],
      },
      {
        label: 'Employee',
        items: [
          {
            label: 'Add New Employee',
            icon: 'pi pi-fw pi-plus',
            url: '/employee',
          },
          { label: 'Show All Employees' },
        ],
      },
    ];
  }
}
