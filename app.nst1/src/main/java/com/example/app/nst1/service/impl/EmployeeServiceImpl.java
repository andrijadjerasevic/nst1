package com.example.app.nst1.service.impl;

import com.example.app.nst1.model.Employee;
import com.example.app.nst1.repository.EmployeeRepository;
import com.example.app.nst1.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee save(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public Optional<Employee> findById(Long id) {
    return employeeRepository.findById(id);
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee update(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public void delete(Long id) {
    employeeRepository.deleteById(id);
  }
}
