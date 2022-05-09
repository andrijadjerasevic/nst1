import { Admin } from './admin';

export class ProjectEvent {
  constructor(
    public projectEventId?: string,
    public projectEventName?: string,
    public projectEventLocation?: string,
    public projectEventDescription?: string,
    public startDate?: string,
    public endDate?: string,
    public admin?: string
  ) {}
}
