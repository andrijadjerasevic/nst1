package com.example.app.nst1.service;

import com.example.app.nst1.model.Admin;
import com.example.app.nst1.model.Employee;
import com.example.app.nst1.repository.EmployeeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeServiceTest {

  @Autowired private EmployeeService employeeService;

  @MockBean private EmployeeRepository employeeRepository;

  private Employee expectedEmployee;
  private List<Employee> expectedEmployees;

  @BeforeAll
  public void beforeAll() {
    expectedEmployee = generateEmployee();
    expectedEmployees = Arrays.asList(expectedEmployee);
  }

  private Employee generateEmployee() {
    //    return new Employee("employeeMail", "firstName", "lastName");
    return new Employee(
        RandomStringUtils.randomAlphabetic(5),
        RandomStringUtils.randomAlphabetic(5),
        RandomStringUtils.randomAlphabetic(5),
        new Admin("adminEmail", "adminPassword"));
  }

  @Test
  public void saveTest() {
    Employee employee = generateEmployee();
    Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
    Employee savedEmployee = employeeService.save(employee);
    Assertions.assertNotNull(savedEmployee);
  }

  @Test
  public void findByTest() {
    Mockito.when(employeeRepository.findByEmail(expectedEmployee.getEmployeeEmail()))
        .thenReturn(Optional.of(expectedEmployee));

    Optional<Employee> foundEmployee = employeeService.findBy(expectedEmployee.getEmployeeEmail());
    Assertions.assertTrue(foundEmployee.isPresent());
    Assertions.assertEquals(expectedEmployee, foundEmployee.get());
  }

  @Test
  public void findAllTest() {
    Mockito.when(employeeRepository.findAll()).thenReturn(expectedEmployees);
    List<Employee> foundEmployees = employeeService.findAll();
    Assertions.assertTrue(foundEmployees != null && !foundEmployees.isEmpty());
    Assertions.assertTrue(foundEmployees.contains(expectedEmployee));
  }

  @Test
  public void updateTest() {
    Optional<Employee> employee = Optional.of(generateEmployee());

    Employee updatedEmployee = new Employee();
    updatedEmployee.setEmployeeEmail(employee.get().getEmployeeEmail());
    updatedEmployee.setFirstName(RandomStringUtils.randomAlphabetic(5));
    updatedEmployee.setLastName(RandomStringUtils.randomAlphabetic(5));
    updatedEmployee.setAdmin(employee.get().getAdmin());

    Mockito.when(employeeRepository.findByEmail(employee.get().getEmployeeEmail()))
        .thenReturn(Optional.of(updatedEmployee));

    employeeRepository.updateEmployee(
        updatedEmployee.getFirstName(),
        updatedEmployee.getLastName(),
        updatedEmployee.getAdmin(),
        updatedEmployee.getEmployeeEmail());

    Mockito.verify(employeeRepository, Mockito.times(1))
        .updateEmployee(
            updatedEmployee.getFirstName(),
            updatedEmployee.getLastName(),
            updatedEmployee.getAdmin(),
            updatedEmployee.getEmployeeEmail());

    Optional<Employee> foundEmployee =
        employeeRepository.findByEmail(employee.get().getEmployeeEmail());

    Assertions.assertTrue(foundEmployee.isPresent());
    Assertions.assertEquals(updatedEmployee, foundEmployee.get());
  }

  @Test
  public void deleteTest() {
    Optional<Employee> employee = Optional.of(generateEmployee());
    Mockito.when(employeeRepository.findByEmail(employee.get().getEmployeeEmail()))
        .thenReturn(employee);

    employeeService.deleteBy(employee.get().getEmployeeEmail());
    Mockito.verify(employeeRepository, Mockito.times(1))
        .deleteByEmail(employee.get().getEmployeeEmail());
  }
}
