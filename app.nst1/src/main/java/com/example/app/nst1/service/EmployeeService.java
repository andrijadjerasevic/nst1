package com.example.app.nst1.service;

import com.example.app.nst1.model.Employee;
import com.example.app.nst1.service.genericOperationService.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService extends GenericOperationsService<Employee, String> {
  @Override
  Employee save(Employee employee);

  @Override
  Optional<Employee> findBy(String email);

  @Override
  List<Employee> findAll();

  @Override
  Employee update(Employee employee);

  @Override
  void deleteBy(String email);
}
