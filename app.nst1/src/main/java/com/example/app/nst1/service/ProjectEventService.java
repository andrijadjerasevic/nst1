package com.example.app.nst1.service;

import com.example.app.nst1.exceptions.ProjectEventException;
import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.service.genericOperationService.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectEventService extends GenericOperationsService<ProjectEvent, String> {
  @Override
  ProjectEvent save(ProjectEvent projectEvent) throws ProjectEventException;

  @Override
  Optional<ProjectEvent> findBy(String id) throws ProjectEventException;

  @Override
  List<ProjectEvent> findAll() throws ProjectEventException;

  @Override
  ProjectEvent update(ProjectEvent projectEvent) throws ProjectEventException;

  @Override
  void deleteBy(String id) throws ProjectEventException;
}
