import { Pipe, PipeTransform } from '@angular/core';
import { ProjectEvent } from '../models/projectEvent.model';

@Pipe({
  name: 'projectEventPipe',
})
export class ProjectEventPipe implements PipeTransform {
  transform(value: ProjectEvent, ...args: unknown[]): string {
    if (!value) {
      return 'N/A';
    }
    return `${value.projectEventId},
    ${value.projectEventName}, 
    ${value.projectEventLocation},
    ${value.projectEventDescription},
    ${value.startDate}
    ${value.endDate}, 
    ${value.admin}`;
  }
}
