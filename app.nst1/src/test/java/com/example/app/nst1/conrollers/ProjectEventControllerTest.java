package com.example.app.nst1.conrollers;

import com.example.app.nst1.controller.ProjectEventController;
import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.service.ProjectEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(ProjectEventController.class)
public class ProjectEventControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProjectEventService projectEventService;

  @Autowired private ObjectMapper objectMapper;

  private ProjectEvent projectEvent;
  private List<ProjectEvent> projectEvents;

  @BeforeEach
  public void setUp() {
    Date startDate = new DateTime(new Date()).toDate();
    Date endDate = new DateTime(new Date()).plus(1).toDate();
    projectEvent =
        new ProjectEvent(
            UUID.randomUUID().toString(),
            "projectEventName",
            "projectEventLocation",
            "projectEventDescription",
            startDate,
            endDate);
    projectEvents = List.of(projectEvent);
  }

  @Test
  public void saveTest() throws Exception {
    Mockito.when(projectEventService.save(projectEvent)).thenReturn(projectEvent);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/projectEvent/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectEvent)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void findByTest() throws Exception {
    Optional<ProjectEvent> foundEvent = Optional.of(projectEvent);
    Mockito.when(projectEventService.findBy(projectEvent.getProjectEventId()))
        .thenReturn(foundEvent);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/projectEvent/get/" + projectEvent.getProjectEventId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectEvent)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.projectEventName", Matchers.equalTo(foundEvent.get().getProjectEventName())));
  }

  @Test
  public void findAllTest() throws Exception {
    Mockito.when(projectEventService.findAll()).thenReturn(projectEvents);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/projectEvent/get/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectEvents)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateTest() throws Exception {
    Mockito.when(projectEventService.update(projectEvent)).thenReturn(projectEvent);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/projectEvent/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectEvent)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void deleteTest() throws Exception {
    projectEventService.deleteBy(projectEvent.getProjectEventId());
    Mockito.verify(projectEventService, Mockito.times(1))
        .deleteBy(projectEvent.getProjectEventId());
    Optional<ProjectEvent> result = projectEventService.findBy(projectEvent.getProjectEventId());
    Assertions.assertEquals(result, Optional.empty());
  }
}
