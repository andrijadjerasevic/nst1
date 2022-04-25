package com.example.app.nst1.service.impl;

import com.example.app.nst1.exceptions.EmployeeException;
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
  public Employee save(Employee employee) throws EmployeeException {
    if (employeeRepository.findByEmail(employee.getEmployeeEmail()).isPresent()) {
      throw new EmployeeException("Employee already exists!");
    }
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
  public Employee update(Employee employee) throws EmployeeException {
    // we want to update just firstName and lastName and admin, not employee email since it is a
    // primary key
    employeeRepository.updateEmployee(
        employee.getFirstName(),
        employee.getLastName(),
        employee.getAdmin(),
        employee.getEmployeeEmail());
    Optional<Employee> updateEmployee = findBy(employee.getEmployeeEmail());
    if (updateEmployee.isPresent()) {
      return updateEmployee.get();
    }
    throw new EmployeeException("Error during employee update! Employee must exists in order to be updated!");
  }

  @Override
  @Transactional
  public void deleteBy(String employeeEmail) {
    employeeRepository.deleteByEmail(employeeEmail);
  }
}
