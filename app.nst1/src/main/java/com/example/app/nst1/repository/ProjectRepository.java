package com.example.app.nst1.repository;

import com.example.app.nst1.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("SELECT p FROM Project p WHERE p.projectId = ?1")
  Optional<Project> findById(String projectId);

  @Modifying
  @Query("DELETE FROM Project p where p.projectId = ?1")
  void deleteById(String projectId);
}
