package com.example.app.nst1.service.impl;

import com.example.app.nst1.model.Project;
import com.example.app.nst1.repository.ProjectRepository;
import com.example.app.nst1.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

  private ProjectRepository projectRepository;

  public ProjectServiceImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public Optional<Project> findById(Long id) {
    return projectRepository.findById(id);
  }

  @Override
  public List<Project> findAll() {
    return projectRepository.findAll();
  }

  @Override
  public Project update(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public void delete(Long id) {
    projectRepository.deleteById(id);
  }
}
