package com.example.app.nst1.service;

import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.repository.ProjectEventRepository;
import com.example.app.nst1.service.calendar.impl.CalendarServiceImpl;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjectEventServiceTest {

  @Autowired private ProjectEventService projectEventService;

  @MockBean private ProjectEventRepository projectEventRepository;

  @MockBean private CalendarServiceImpl calendarService;

  @MockBean private Calendar service;

  private Event googleEvent;
  private List<Event> googleEvents;

  private ProjectEvent expectedProjectEvent;
  private List<ProjectEvent> expectedProjectEvents;

  @BeforeAll
  public void beforeAll() throws Exception {
    expectedProjectEvent = generateProjectEvent();
    expectedProjectEvents = Arrays.asList(expectedProjectEvent);

    googleEvent = generateGoogleEvent(expectedProjectEvent);
    googleEvents = Arrays.asList(googleEvent);

    Mockito.when(calendarService.initializeNewAuthorization()).thenReturn(service);
    Mockito.when(calendarService.createEvent(expectedProjectEvent)).thenReturn(googleEvent);
    Mockito.when(calendarService.sendEventToCalendar(service, googleEvent)).thenReturn(googleEvent);
    Mockito.when(calendarService.findEvent(service, googleEvent.getId())).thenReturn(googleEvent);
    Mockito.when(calendarService.getAllGoogleEvents(service)).thenReturn(googleEvents);
    Mockito.when(
            calendarService.updateEvent(
                service, expectedProjectEvent.getProjectEventId(), googleEvent))
        .thenReturn(googleEvent);
    CalendarServiceImpl calendarServiceMock = Mockito.mock(CalendarServiceImpl.class);
    Mockito.doNothing()
        .when(calendarServiceMock)
        .deleteEvent(service, expectedProjectEvent.getProjectEventId());
  }

  private ProjectEvent generateProjectEvent() {
    return new ProjectEvent(
        UUID.randomUUID().toString(),
        "projectEventName",
        "projectEventLocation",
        "projectEventDescription",
        new DateTime().plusDays(1).toDate(),
        new DateTime().plusDays(2).toDate());
  }

  private Event generateGoogleEvent(ProjectEvent projectEvent) {
    return new Event()
        .setId(UUID.randomUUID().toString())
        .setSummary(projectEvent.getProjectEventName())
        .setLocation(projectEvent.getProjectEventLocation())
        .setDescription(projectEvent.getProjectEventDescription())
        .setColorId("6");
  }

  @Test
  public void saveTest() throws Exception {

    Mockito.when(projectEventRepository.save(expectedProjectEvent))
        .thenReturn(expectedProjectEvent);

    ProjectEvent savedProjectEvent = projectEventService.save(expectedProjectEvent);
    Assertions.assertNotNull(savedProjectEvent);
    Assertions.assertEquals(expectedProjectEvent, savedProjectEvent);
  }

  @Test
  public void findByTest() {
    Mockito.when(projectEventRepository.findById(expectedProjectEvent.getProjectEventId()))
        .thenReturn(Optional.of(expectedProjectEvent));

    Optional<ProjectEvent> foundProjectEvent =
        projectEventService.findBy(expectedProjectEvent.getProjectEventId());
    Assertions.assertTrue(foundProjectEvent.isPresent());
    Assertions.assertEquals(expectedProjectEvent, foundProjectEvent.get());
  }

  @Test
  public void findAllTest() {
    Mockito.when(projectEventRepository.findAll()).thenReturn(expectedProjectEvents);
    List<ProjectEvent> foundProjectEvents = projectEventService.findAll();
    Assertions.assertTrue(foundProjectEvents != null && !foundProjectEvents.isEmpty());
    Assertions.assertTrue(foundProjectEvents.contains(expectedProjectEvent));
  }

  @Test
  public void updateTest() {
    Optional<ProjectEvent> projectEvent = Optional.of(generateProjectEvent());

    ProjectEvent updatedProjectEvent = new ProjectEvent();
    updatedProjectEvent.setProjectEventId(projectEvent.get().getProjectEventId());
    // updated fields
    updatedProjectEvent.setProjectEventName(RandomStringUtils.randomAlphabetic(5));
    updatedProjectEvent.setProjectEventDescription(RandomStringUtils.randomAlphabetic(5));
    updatedProjectEvent.setProjectEventLocation(RandomStringUtils.randomAlphabetic(5));

    updatedProjectEvent.setStartDate(projectEvent.get().getStartDate());
    updatedProjectEvent.setEndDate(projectEvent.get().getEndDate());

    //    when we update project, we search for that updated project
    Mockito.when(projectEventRepository.findById(projectEvent.get().getProjectEventId()))
        .thenReturn(Optional.of(updatedProjectEvent));

    //    perform updated via save, we keep id, everything else is updatable
    projectEventRepository.save(updatedProjectEvent);

    Mockito.verify(projectEventRepository, Mockito.times(1)).save(updatedProjectEvent);

    Optional<ProjectEvent> foundProjectEvent =
        projectEventRepository.findById(projectEvent.get().getProjectEventId());

    Assertions.assertTrue(foundProjectEvent.isPresent());
    Assertions.assertEquals(updatedProjectEvent, foundProjectEvent.get());
  }

  @Test
  public void deleteTest() {
    Mockito.doNothing()
        .when(projectEventRepository)
        .deleteById(expectedProjectEvent.getProjectEventId());
    projectEventService.deleteBy(expectedProjectEvent.getProjectEventId());
    Mockito.verify(projectEventRepository, Mockito.times(1))
        .deleteById(expectedProjectEvent.getProjectEventId());
  }
}
