package com.example.app.nst1.controller;

import com.example.app.nst1.model.Employee;
import com.example.app.nst1.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("employee")
public class EmployeeController {

  private EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping("save")
  public @ResponseBody ResponseEntity<Employee> save(@RequestBody @Valid Employee employee) {
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
  }

  @GetMapping("get/{id}")
  public @ResponseBody ResponseEntity<Employee> findById(@PathVariable Long id) {
    Optional<Employee> foundEmployee = employeeService.findById(id);
    if (foundEmployee.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(foundEmployee.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("get/all")
  public @ResponseBody ResponseEntity<List<Employee>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
  }

  @PostMapping("update")
  public @ResponseBody ResponseEntity<Employee> update(@RequestBody @Valid Employee employee) {
    if (employee.getEmployeeId() != 0) {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(employee));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("delete/{id}")
  public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
    employeeService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted");
  }
}
