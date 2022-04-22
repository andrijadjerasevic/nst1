package com.example.app.nst1.conrollers;

import com.example.app.nst1.controller.ProjectController;
import com.example.app.nst1.model.Project;
import com.example.app.nst1.service.ProjectService;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProjectService projectService;

  @Autowired private ObjectMapper objectMapper;
  Project project;
  List<Project> projects;

  @BeforeEach
  public void before() {
    Date startDate = new DateTime(new Date()).toDate();
    Date endDate = new DateTime(new Date()).plus(1).toDate();
    project =
        new Project(1L, "projectName", "projectLocation", "projectDescription", startDate, endDate);
    projects = Arrays.asList(project);
  }

  @Test
  public void saveTest() throws Exception {
    Mockito.when(projectService.save(project)).thenReturn(project);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/project/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void findBy() throws Exception {
    Optional<Project> foundProject = Optional.of(project);
    Mockito.when(projectService.findBy(project.getProjectId())).thenReturn(foundProject);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/project/get/" + project.getProjectId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.projectName", Matchers.equalTo(foundProject.get().getProjectName())));
  }

  @Test
  public void findAllTest() throws Exception {
    Mockito.when(projectService.findAll()).thenReturn(projects);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/project/get/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projects)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateTest() throws Exception {
    Mockito.when(projectService.update(project)).thenReturn(project);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/project/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void deleteTest() throws Exception {
    projectService.deleteBy(project.getProjectId());
    Mockito.verify(projectService, Mockito.times(1)).deleteBy(project.getProjectId());
    Optional<Project> result = projectService.findBy(project.getProjectId());
    Assertions.assertEquals(result, Optional.empty());
  }
}
