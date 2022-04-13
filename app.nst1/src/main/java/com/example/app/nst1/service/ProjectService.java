package com.example.app.nst1.service;

import com.example.app.nst1.model.Project;
import com.example.app.nst1.service.gemeric.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService extends GenericOperationsService<Project, Long> {
  @Override
  Project save(Project project);

  @Override
  Optional<Project> findById(Long id);

  @Override
  List<Project> findAll();

  @Override
  Project update(Project project);

  @Override
  void delete(Long id);
}
