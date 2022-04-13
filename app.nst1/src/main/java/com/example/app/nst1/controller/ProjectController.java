package com.example.app.nst1.controller;

import com.example.app.nst1.model.Project;
import com.example.app.nst1.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("project")
public class ProjectController {

  private ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping("save")
  public @ResponseBody ResponseEntity<Project> save(@RequestBody @Valid Project project) {
    return ResponseEntity.status(HttpStatus.OK).body(projectService.save(project));
  }

  @GetMapping("get/{id}")
  public @ResponseBody ResponseEntity<Project> findById(@PathVariable Long id) {
    Optional<Project> foundProject = projectService.findById(id);
    if (foundProject.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(foundProject.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("get/all")
  public @ResponseBody ResponseEntity<List<Project>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll());
  }

  @PostMapping("update")
  public @ResponseBody ResponseEntity<Project> update(@RequestBody @Valid Project project) {
    if (project.getProjectId() != 0) {
      return ResponseEntity.status(HttpStatus.OK).body(projectService.update(project));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("delete/{id}")
  public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
    projectService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted");
  }
}
