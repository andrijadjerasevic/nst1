package com.example.app.nst1.repository;

import com.example.app.nst1.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {

  @Query("SELECT a FROM Admin a WHERE a.email = ?1 AND a.password = ?2")
  Admin login(String email, String password);
}