package com.example.app.nst1.service;

import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.service.genericOperationService.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectEventService extends GenericOperationsService<ProjectEvent, String> {
  @Override
  ProjectEvent save(ProjectEvent projectEvent) throws Exception;

  @Override
  Optional<ProjectEvent> findBy(String id) throws Exception;

  @Override
  List<ProjectEvent> findAll() throws Exception;

  @Override
  ProjectEvent update(ProjectEvent projectEvent) throws Exception;

  @Override
  void deleteBy(String id) throws Exception;
}
