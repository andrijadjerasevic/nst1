package com.example.app.nst1.repository;

import com.example.app.nst1.model.Employee;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeRepositoryTest {

  @Autowired private EmployeeRepository employeeRepository;
  private Employee expectedEmployee;
  private List<Employee> expectedEmployees;

  @BeforeAll
  public void beforeAll() {
    expectedEmployee = generateEmployee();
    expectedEmployees = Arrays.asList(expectedEmployee);
    employeeRepository.save(expectedEmployee);
  }

  @Test
  public void saveTest() {
    Employee expectedEmployee = generateEmployee();
    Employee savedEmployee = employeeRepository.save(expectedEmployee);
    Assertions.assertNotNull(savedEmployee);
    Assertions.assertEquals(expectedEmployee, savedEmployee);
  }

  @Test
  public void findByTest() {
    Optional<Employee> foundEmployee =
        employeeRepository.findByEmail(expectedEmployee.getEmployeeEmail());
    Assertions.assertTrue(foundEmployee.isPresent());
    Assertions.assertEquals(expectedEmployee, foundEmployee.get());
  }

  @Test
  public void findAllTest() {
    List<Employee> foundEmployees = employeeRepository.findAll();
    Assertions.assertNotNull(foundEmployees);
    Assertions.assertFalse(foundEmployees.isEmpty());
    Assertions.assertTrue(foundEmployees.contains(expectedEmployee));
  }

  @Test
  @Transactional
  public void updateTest() {
    Employee updatedEmployee = generateEmployee();
    updatedEmployee.setEmployeeEmail(expectedEmployee.getEmployeeEmail());

    employeeRepository.updateEmployee(
        updatedEmployee.getFirstName(),
        updatedEmployee.getLastName(),
        updatedEmployee.getAdmin(),
        updatedEmployee.getEmployeeEmail());

    Optional<Employee> foundUpdatedEmployee =
        employeeRepository.findByEmail(expectedEmployee.getEmployeeEmail());

    Assertions.assertTrue(foundUpdatedEmployee.isPresent());
    Assertions.assertEquals(updatedEmployee, foundUpdatedEmployee.get());
  }

  @Test
  @Transactional
  public void deleteTest() {
    employeeRepository.deleteByEmail(expectedEmployee.getEmployeeEmail());
    Optional<Employee> deletedEmployee =
        employeeRepository.findByEmail(expectedEmployee.getEmployeeEmail());
    Assertions.assertFalse(deletedEmployee.isPresent());
  }

  private Employee generateEmployee() {
    return new Employee(
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(10),
        RandomStringUtils.randomAlphabetic(10));
  }
}
