package com.example.app.nst1.service;

import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.service.genericOperationService.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectEventService extends GenericOperationsService<ProjectEvent, String> {
  @Override
  ProjectEvent save(ProjectEvent projectEvent);

  @Override
  Optional<ProjectEvent> findBy(String id);

  @Override
  List<ProjectEvent> findAll();

  @Override
  ProjectEvent update(ProjectEvent projectEvent);

  @Override
  void deleteBy(String id);
}
