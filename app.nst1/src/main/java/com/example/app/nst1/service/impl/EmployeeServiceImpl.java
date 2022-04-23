package com.example.app.nst1.service.impl;

import com.example.app.nst1.model.Employee;
import com.example.app.nst1.repository.EmployeeRepository;
import com.example.app.nst1.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public Optional<Employee> findBy(String employeeEmail) {
    return employeeRepository.findByEmail(employeeEmail);
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional
  public Employee update(Employee employee) {
    // we want to update just firstName and lastName and admin, not employee email since it is a
    // primary key
    employeeRepository.updateEmployee(
        employee.getFirstName(),
        employee.getLastName(),
        employee.getAdmin(),
        employee.getEmployeeEmail());
    Optional<Employee> updateEmployee = findBy(employee.getEmployeeEmail());
    return updateEmployee.orElse(new Employee());
  }

  @Override
  @Transactional
  public void deleteBy(String employeeEmail) {
    employeeRepository.deleteByEmail(employeeEmail);
  }
}
