package com.example.app.nst1.conrollers;

import com.example.app.nst1.controller.EmployeeController;
import com.example.app.nst1.model.Employee;
import com.example.app.nst1.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private EmployeeService employeeService;

  @Autowired private ObjectMapper objectMapper;

  Employee employee;
  List<Employee> employees;

  @BeforeEach
  public void before() {
    employee = new Employee(1L, "employeeFristName", "employeeLastName", "employeeEmail");
    employees = Arrays.asList(employee);
  }

  @Test
  public void saveTest() throws Exception {
    Mockito.when(employeeService.save(employee)).thenReturn(employee);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/employee/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void findById() throws Exception {
    Optional<Employee> foundEmployee = Optional.of(employee);
    Mockito.when(employeeService.findById(employee.getEmployeeId())).thenReturn(foundEmployee);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/employee/get/" + employee.getEmployeeId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.firstName", Matchers.equalTo(foundEmployee.get().getFirstName())))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.lastName", Matchers.equalTo(foundEmployee.get().getLastName())));
  }

  @Test
  public void findAllTest() throws Exception {
    Mockito.when(employeeService.findAll()).thenReturn(employees);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/employee/get/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employees)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateTest() throws Exception {
    Mockito.when(employeeService.update(employee)).thenReturn(employee);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/employee/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void deleteTest() throws Exception {
    employeeService.delete(employee.getEmployeeId());
    Mockito.verify(employeeService, Mockito.times(1)).delete(employee.getEmployeeId());
    Optional<Employee> result = employeeService.findById(employee.getEmployeeId());
    Assertions.assertEquals(result, Optional.empty());
  }
}
