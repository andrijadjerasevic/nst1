package com.example.app.nst1.repository;

import com.example.app.nst1.model.ProjectEvent;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjectEventRepositoryTest {

  @Autowired private ProjectEventRepository projectEventRepository;

  private ProjectEvent expectedProjectEvent;
  private List<ProjectEvent> expectedProjectEvents;

  @BeforeAll
  public void beforeAll() {
    expectedProjectEvent = generateProjectEvent();
    expectedProjectEvents = Arrays.asList(expectedProjectEvent);
    projectEventRepository.save(expectedProjectEvent);
  }

  @Test
  public void saveTest() {
    ProjectEvent expectedProjectEvent = generateProjectEvent();
    ProjectEvent savedProjectEvent = projectEventRepository.save(expectedProjectEvent);
    Assertions.assertNotNull(savedProjectEvent);
    Assertions.assertEquals(expectedProjectEvent, savedProjectEvent);
  }

  @Test
  public void findByTest() {
    Optional<ProjectEvent> foundProjectEvent =
        projectEventRepository.findById(expectedProjectEvent.getProjectEventId());
    Assertions.assertTrue(foundProjectEvent.isPresent());
    Assertions.assertEquals(expectedProjectEvent, foundProjectEvent.get());
  }

  @Test
  public void findAllTest() {
    List<ProjectEvent> foundProjectEvents = projectEventRepository.findAll();
    Assertions.assertNotNull(foundProjectEvents);
    Assertions.assertFalse(foundProjectEvents.isEmpty());
    Assertions.assertTrue(foundProjectEvents.contains(expectedProjectEvent));
  }

  @Test
  @Transactional
  public void updateTest() {
    ProjectEvent updatedProjectEvent = projectEventRepository.save(generateProjectEvent());
    Assertions.assertNotNull(updatedProjectEvent);
    Optional<ProjectEvent> expectedProjectEvent =
        projectEventRepository.findById(updatedProjectEvent.getProjectEventId());
    Assertions.assertTrue(expectedProjectEvent.isPresent());
    Assertions.assertEquals(expectedProjectEvent.get(), updatedProjectEvent);
  }

  @Test
  @Transactional
  public void deleteTest() {
    projectEventRepository.deleteById(expectedProjectEvent.getProjectEventId());
    Optional<ProjectEvent> deletedProjectEvent =
        projectEventRepository.findById(expectedProjectEvent.getProjectEventId());
    Assertions.assertFalse(deletedProjectEvent.isPresent());
  }

  private ProjectEvent generateProjectEvent() {
    Date startDate = new DateTime().plusDays(1).toDate();
    Date endDate = new DateTime().plusDays(2).toDate();
    return new ProjectEvent(
        UUID.randomUUID().toString(),
        "projectEventName",
        "projectEventLocation",
        "projectEventDescription",
        startDate,
        endDate);
  }
}
