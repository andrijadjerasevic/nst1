package com.example.app.nst1.repository;

import com.example.app.nst1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("SELECT e FROM Employee e WHERE e.employeeEmail = ?1")
  Optional<Employee> findByEmail(String email);


  @Modifying
  @Query("DELETE FROM Employee e WHERE e.employeeEmail = ?1")
  void deleteByEmail(String employeeEmail);
}
