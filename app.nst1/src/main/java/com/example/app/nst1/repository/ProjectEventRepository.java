package com.example.app.nst1.repository;

import com.example.app.nst1.model.ProjectEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectEventRepository extends JpaRepository<ProjectEvent, Long> {

  @Query("SELECT e FROM ProjectEvent e WHERE e.projectEventId = ?1")
  Optional<ProjectEvent> findById(String projectEventId);

  @Modifying
  @Query("DELETE FROM ProjectEvent e where e.projectEventId = ?1")
  void deleteById(String projectEventId);
}
