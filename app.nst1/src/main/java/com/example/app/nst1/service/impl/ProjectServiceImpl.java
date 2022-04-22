package com.example.app.nst1.service.impl;

import com.example.app.nst1.model.Project;
import com.example.app.nst1.repository.ProjectRepository;
import com.example.app.nst1.service.ProjectService;
import com.example.app.nst1.service.calendar.impl.CalendarServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

  private ProjectRepository projectRepository;
  private CalendarServiceImpl calendarService;

  public ProjectServiceImpl(
      ProjectRepository projectRepository, CalendarServiceImpl calendarService) {
    this.projectRepository = projectRepository;
    this.calendarService = calendarService;
  }

  @Override
  public Project save(Project project) {
    try {
      String googleEventId =
          calendarService
              .createEvent(calendarService.initializeNewAuthorization(), project)
              .getId();
      project.setProjectId(googleEventId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectRepository.save(project);
  }

  @Override
  public Optional<Project> findBy(String id) {
    return projectRepository.findById(id);
  }

  @Override
  public List<Project> findAll() {
    try {
      calendarService.listUpcomingEvents(calendarService.initializeNewAuthorization());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectRepository.findAll();
  }

  @Override
  public Project update(Project project) {
    return projectRepository.save(project);
  }

  @Override
  @Transactional
  public void deleteBy(String id) {
    projectRepository.deleteById(id);
  }
}
