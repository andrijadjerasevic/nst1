package com.example.app.nst1.service;

import com.example.app.nst1.model.Project;
import com.example.app.nst1.service.genericOperationService.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService extends GenericOperationsService<Project, String> {
  @Override
  Project save(Project project);

  @Override
  Optional<Project> findBy(String id);

  @Override
  List<Project> findAll();

  @Override
  Project update(Project project);

  @Override
  void deleteBy(String id);
}
