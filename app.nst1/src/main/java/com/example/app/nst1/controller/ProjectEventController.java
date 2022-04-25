package com.example.app.nst1.controller;

import com.example.app.nst1.exceptions.ProjectEventException;
import com.example.app.nst1.model.ProjectEvent;
import com.example.app.nst1.service.ProjectEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("projectEvent")
public class ProjectEventController {

  private ProjectEventService projectEventService;

  public ProjectEventController(ProjectEventService projectEventService) {
    this.projectEventService = projectEventService;
  }

  @PostMapping("save")
  public @ResponseBody ResponseEntity<ProjectEvent> save(
      @RequestBody @Valid ProjectEvent projectEvent) throws ProjectEventException {
    return ResponseEntity.status(HttpStatus.OK).body(projectEventService.save(projectEvent));
  }

  @GetMapping("get/{id}")
  public @ResponseBody ResponseEntity<ProjectEvent> findById(@PathVariable String id)
      throws ProjectEventException {
    Optional<ProjectEvent> foundEvent = projectEventService.findBy(id);
    if (foundEvent.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(foundEvent.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("get/all")
  public @ResponseBody ResponseEntity<List<ProjectEvent>> findAll() throws ProjectEventException {
    return ResponseEntity.status(HttpStatus.OK).body(projectEventService.findAll());
  }

  @PostMapping("update")
  public @ResponseBody ResponseEntity<ProjectEvent> update(
      @RequestBody @Valid ProjectEvent projectEvent) throws ProjectEventException {
    if (projectEvent.getProjectEventId() != null) {
      return ResponseEntity.status(HttpStatus.OK).body(projectEventService.update(projectEvent));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("delete/{id}")
  public @ResponseBody ResponseEntity delete(@PathVariable String id) throws ProjectEventException {
    projectEventService.deleteBy(id);
    return ResponseEntity.status(HttpStatus.OK).body("EVENT DELETED");
  }
}
