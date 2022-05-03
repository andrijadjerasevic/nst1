package com.example.app.nst1.service.impl;

import com.example.app.nst1.exceptions.ProjectEventException;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.repository.ProjectEventRepository;
import com.example.app.nst1.service.ProjectEventService;
import com.example.app.nst1.service.calendar.impl.CalendarServiceImpl;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectEventServiceImpl implements ProjectEventService {

  private static final Logger logger = LoggerFactory.getLogger(ProjectEventServiceImpl.class);

  private ProjectEventRepository projectEventRepository;
  private CalendarServiceImpl calendarService;

  public ProjectEventServiceImpl(
      ProjectEventRepository projectEventRepository, CalendarServiceImpl calendarService) {
    this.projectEventRepository = projectEventRepository;
    this.calendarService = calendarService;
    syncData();
  }

  @Override
  public ProjectEvent save(ProjectEvent projectEvent) throws ProjectEventException {
    if (projectEventRepository.findById(projectEvent.getProjectEventId()).isPresent())
      throw new ProjectEventException("Event already exists!");
    try {
      // we first create google event from project event and then send it to google calendar
      Calendar service = calendarService.initializeNewAuthorization();
      Event googleEvent =
          calendarService.sendEventToCalendar(service, calendarService.createEvent(projectEvent));

      logger.info(
          "GOOGLE EVENT SAVED: summary = {}, htmlLink = {}",
          googleEvent.getSummary(),
          googleEvent.getHtmlLink());

      projectEvent.setProjectEventId(googleEvent.getId());
    } catch (Exception e) {
      throw new ProjectEventException("Error while saving event!", e);
    }
    return projectEventRepository.save(projectEvent);
  }

  @Override
  public Optional<ProjectEvent> findBy(String id) throws ProjectEventException {
    try {
      Calendar service = calendarService.initializeNewAuthorization();
      Event googleFoundEvent = calendarService.findEvent(service, id);
      logger.info(
          "GOOGLE EVENT FOUND: summary = {}, htmlLink = {}",
          googleFoundEvent.getSummary(),
          googleFoundEvent.getHtmlLink());
    } catch (Exception e) {
      throw new ProjectEventException("Error while finding event with id = " + id, e);
    }
    return projectEventRepository.findById(id);
  }

  @Override
  public List<ProjectEvent> findAll() throws ProjectEventException {
    List<Event> googleEvents;
    List<ProjectEvent> projectEvents;
    try {
      googleEvents =
          calendarService.getAllGoogleEvents(calendarService.initializeNewAuthorization());
      logger.info("ALL GOOGLE EVENTS");
      googleEvents.forEach(
          event -> {
            logger.info(
                "GOOGLE EVENT: summary = {}, htmlLink = {}",
                event.getSummary(),
                event.getHtmlLink());
          });
      projectEvents = projectEventRepository.findAll();
    } catch (Exception e) {
      throw new ProjectEventException("Error while fetching all events", e);
    }
    return projectEvents;
  }

  private void syncData() {
    // TODO: 03-May-22 do better this method
    try {
      projectEventRepository.deleteAll();

      List<ProjectEvent> projectEvents = findAll();
      List<Event> googleEvents =
          calendarService.getAllGoogleEvents(calendarService.initializeNewAuthorization());
      for (Event googleEvent : googleEvents) {
        ProjectEvent foundProjectEvent =
            projectEvents.stream()
                .filter(
                    projectEvent ->
                        Objects.equals(projectEvent.getProjectEventId(), googleEvent.getId()))
                .findAny()
                .orElse(null);
        if (foundProjectEvent == null && findBy(googleEvent.getId()).isEmpty()) {
          //        insert in database
          // TODO: 03-May-22 fix mail
          projectEventRepository.save(
              createProjectEventFromGoogleEvent(googleEvent, CalendarServiceImpl.DEFAULT_MAIL));
        }
      }
    } catch (Exception e) {
      logger.error("ERROR WHILE DATA SYNCH");
      //      delete this
      e.printStackTrace();
    }
  }

  private ProjectEvent createProjectEventFromGoogleEvent(Event googleEvent, String adminEmail) {
    Instant startMills =
        Instant.ofEpochMilli(
            googleEvent.getStart().getDateTime() != null
                ? googleEvent.getStart().getDateTime().getValue()
                : googleEvent.getStart().getDate().getValue());
    Instant endMills =
        Instant.ofEpochMilli(
            googleEvent.getEnd().getDateTime() != null
                ? googleEvent.getStart().getDateTime().getValue()
                : googleEvent.getStart().getDate().getValue());
    Date startDate = Date.from(startMills);
    Date endDate = Date.from(endMills);

    Admin admin = new Admin(adminEmail);
    return new ProjectEvent(
        googleEvent.getId(),
        googleEvent.getSummary(),
        googleEvent.getLocation(),
        googleEvent.getDescription(),
        startDate,
        endDate,
        admin);
  }

  @Override
  @Transactional
  public ProjectEvent update(ProjectEvent updatedProjectEvent) throws ProjectEventException {
    try {
      // we first create google event from updated project event and update it in google
      // calendar
      Calendar service = calendarService.initializeNewAuthorization();
      Event googleUpdateEvent =
          calendarService.updateEvent(
              service,
              updatedProjectEvent.getProjectEventId(),
              calendarService.createEvent(updatedProjectEvent));

      logger.info(
          "GOOGLE EVENT UPDATED: summary = {}, htmlLink = {}",
          googleUpdateEvent.getSummary(),
          googleUpdateEvent.getHtmlLink());
    } catch (Exception e) {
      throw new ProjectEventException(
          "Error while updating event with id = " + updatedProjectEvent.getProjectEventId(), e);
    }

    return projectEventRepository.save(updatedProjectEvent);
  }

  @Override
  @Transactional
  public void deleteBy(String id) throws ProjectEventException {
    try {
      // delete Event from Google Calendar
      calendarService.deleteEvent(calendarService.initializeNewAuthorization(), id);
      logger.info("GOOGLE EVENT DELETED");
    } catch (Exception e) {
      throw new ProjectEventException("Error while deleting  event with id = " + id, e);
    }
    // delete Event form Database
    projectEventRepository.deleteById(id);
  }
}
