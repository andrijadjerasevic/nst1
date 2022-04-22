package com.example.app.nst1.repository;

import com.example.app.nst1.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

  @Query("SELECT a FROM Admin a WHERE a.adminEmail = ?1 AND a.adminPassword = ?2")
  Admin login(String adminEmail, String adminPassword);

  @Query("SELECT a FROM Admin a WHERE a.adminEmail = ?1")
  Optional<Admin> findByEmail(String adminEmail);

  @Modifying
  @Query("DELETE FROM Admin a WHERE a.adminEmail = ?1")
  void deleteByEmail(String adminEmail);
}
