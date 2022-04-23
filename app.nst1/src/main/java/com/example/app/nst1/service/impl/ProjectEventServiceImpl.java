package com.example.app.nst1.service.impl;

import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.repository.ProjectEventRepository;
import com.example.app.nst1.service.ProjectEventService;
import com.example.app.nst1.service.calendar.impl.CalendarServiceImpl;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectEventServiceImpl implements ProjectEventService {

  private ProjectEventRepository projectEventRepository;
  private CalendarServiceImpl calendarService;

  public ProjectEventServiceImpl(
      ProjectEventRepository projectEventRepository, CalendarServiceImpl calendarService) {
    this.projectEventRepository = projectEventRepository;
    this.calendarService = calendarService;
  }

  @Override
  public ProjectEvent save(ProjectEvent projectEvent) {
    try {
      // we first create google event from project event and then send it to google calendar
      Calendar service = calendarService.initializeNewAuthorization();
      Event googleEvent =
          calendarService.sendEventToCalendar(service, calendarService.createEvent(projectEvent));

      System.out.println("GOOGLE SAVED EVENT = " + googleEvent);

      projectEvent.setProjectEventId(googleEvent.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectEventRepository.save(projectEvent);
  }

  @Override
  public Optional<ProjectEvent> findBy(String id) {
    try {
      Event googleFoundEvent =
          calendarService.findEvent(calendarService.initializeNewAuthorization(), id);
      System.out.println("GOOGLE FOUND EVENT = " + googleFoundEvent);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectEventRepository.findById(id);
  }

  @Override
  public List<ProjectEvent> findAll() {
    try {
      List<Event> googleEvents =
          calendarService.getAllGoogleEvents(calendarService.initializeNewAuthorization());
      System.out.println("ALL GOOGLE EVENTS = " + googleEvents);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectEventRepository.findAll();
  }

  @Override
  public ProjectEvent update(ProjectEvent updatedProjectEvent) {
    try {
      // we first create google event from updated project event and update it in google
      // calendar
      Calendar service = calendarService.initializeNewAuthorization();
      Event googleUpdateEvent =
          calendarService.updateEvent(
              service,
              updatedProjectEvent.getProjectEventId(),
              calendarService.createEvent(updatedProjectEvent));

      System.out.println("GOOGLE UPDATED EVENT = " + googleUpdateEvent);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return projectEventRepository.save(updatedProjectEvent);
  }

  @Override
  @Transactional
  public void deleteBy(String id) {
    try {
      // delete Event from Google Calendar
      calendarService.deleteEvent(calendarService.initializeNewAuthorization(), id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // delete Event form Database
    projectEventRepository.deleteById(id);
  }
}
