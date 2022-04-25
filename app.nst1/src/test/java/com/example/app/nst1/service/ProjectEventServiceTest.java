package com.example.app.nst1.service;

import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.repository.ProjectEventRepository;
import com.example.app.nst1.service.calendar.impl.CalendarServiceImpl;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ProjectEventServiceTest {

  @Autowired private ProjectEventService projectEventService;

  @MockBean private ProjectEventRepository projectEventRepository;

  @MockBean private CalendarServiceImpl calendarService;

  @MockBean private Calendar service;

  private Event googleEvent;
  private List<Event> googleEvents;

  private ProjectEvent expectedProjectEvent;
  private List<ProjectEvent> expectedProjectEvents;

  @BeforeEach
  public void setUp() throws Exception {
    expectedProjectEvent = generateProjectEvent();
    expectedProjectEvents = Arrays.asList(expectedProjectEvent);
    googleEvent =
        generateGoogleEvent(expectedProjectEvent, expectedProjectEvent.getProjectEventId());
    googleEvents = Arrays.asList(googleEvent);
    Mockito.when(calendarService.initializeNewAuthorization()).thenReturn(service);
    Mockito.when(calendarService.createEvent(ArgumentMatchers.any(ProjectEvent.class)))
        .thenReturn(googleEvent);
    Mockito.when(
            calendarService.sendEventToCalendar(
                ArgumentMatchers.any(Calendar.class), ArgumentMatchers.any(Event.class)))
        .thenReturn(googleEvent);
    Mockito.when(
            calendarService.findEvent(
                ArgumentMatchers.any(Calendar.class), ArgumentMatchers.anyString()))
        .thenReturn(googleEvent);
    Mockito.when(calendarService.getAllGoogleEvents(ArgumentMatchers.any(Calendar.class)))
        .thenReturn(googleEvents);
    Mockito.when(
            calendarService.updateEvent(
                ArgumentMatchers.any(Calendar.class),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Event.class)))
        .thenReturn(googleEvent);
    CalendarServiceImpl calendarServiceMock = Mockito.mock(CalendarServiceImpl.class);
    Mockito.doNothing()
        .when(calendarServiceMock)
        .deleteEvent(ArgumentMatchers.any(Calendar.class), ArgumentMatchers.anyString());
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

  private Event generateGoogleEvent(ProjectEvent projectEvent, String googleEventId) {
    return new Event()
        .setId(googleEventId)
        .setSummary(projectEvent.getProjectEventName())
        .setLocation(projectEvent.getProjectEventLocation())
        .setDescription(projectEvent.getProjectEventDescription())
        .setHtmlLink("https://google.com")
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
  public void findByTest() throws Exception {
    Mockito.when(projectEventRepository.findById(expectedProjectEvent.getProjectEventId()))
        .thenReturn(Optional.of(expectedProjectEvent));

    Optional<ProjectEvent> foundProjectEvent =
        projectEventService.findBy(expectedProjectEvent.getProjectEventId());
    Assertions.assertTrue(foundProjectEvent.isPresent());
    Assertions.assertEquals(expectedProjectEvent, foundProjectEvent.get());
  }

  @Test
  public void findAllTest() throws Exception {
    Mockito.when(projectEventRepository.findAll()).thenReturn(expectedProjectEvents);
    List<ProjectEvent> foundProjectEvents = projectEventService.findAll();
    Assertions.assertTrue(foundProjectEvents != null && !foundProjectEvents.isEmpty());
    Assertions.assertTrue(foundProjectEvents.contains(expectedProjectEvent));
  }

  @Test
  public void updateTest() throws Exception {
    Optional<ProjectEvent> projectEvent = Optional.of(generateProjectEvent());

    ProjectEvent updatedProjectEvent = new ProjectEvent();
    updatedProjectEvent.setProjectEventId(projectEvent.get().getProjectEventId());
    // updated fields
    updatedProjectEvent.setProjectEventName(RandomStringUtils.randomAlphabetic(5));
    updatedProjectEvent.setProjectEventDescription(RandomStringUtils.randomAlphabetic(5));
    updatedProjectEvent.setProjectEventLocation(RandomStringUtils.randomAlphabetic(5));
    updatedProjectEvent.setStartDate(projectEvent.get().getStartDate());
    updatedProjectEvent.setEndDate(projectEvent.get().getEndDate());

    Mockito.when(projectEventRepository.findById(updatedProjectEvent.getProjectEventId()))
        .thenReturn(Optional.of(updatedProjectEvent));

    projectEventService.update(updatedProjectEvent);

    Mockito.verify(projectEventRepository, Mockito.times(1)).save(updatedProjectEvent);

    Optional<ProjectEvent> foundProjectEvent =
        projectEventRepository.findById(projectEvent.get().getProjectEventId());

    Assertions.assertTrue(foundProjectEvent.isPresent());
    Assertions.assertEquals(updatedProjectEvent, foundProjectEvent.get());
  }

  @Test
  public void deleteTest() throws Exception {
    Mockito.doNothing()
        .when(projectEventRepository)
        .deleteById(expectedProjectEvent.getProjectEventId());
    projectEventService.deleteBy(expectedProjectEvent.getProjectEventId());
    Mockito.verify(projectEventRepository, Mockito.times(1))
        .deleteById(expectedProjectEvent.getProjectEventId());
  }
}
