package com.example.app.nst1.controller;

import com.example.app.nst1.exceptions.EmployeeException;
import com.example.app.nst1.model.Employee;
import com.example.app.nst1.service.EmployeeService;
import org.json.JSONObject;
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
  public @ResponseBody ResponseEntity<Employee> save(@RequestBody @Valid Employee employee)
      throws EmployeeException {
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
  }

  @PostMapping("getBy/email")
  public @ResponseBody ResponseEntity<Employee> findBy(@RequestBody @Valid String employeeEmail) {
    JSONObject jsonObject = new JSONObject(employeeEmail);
    String email = jsonObject.get("employee").toString();
    Optional<Employee> foundEmployee = employeeService.findBy(email);
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
  public @ResponseBody ResponseEntity<Employee> update(@RequestBody @Valid Employee employee)
      throws EmployeeException {
    if (employee.getEmployeeEmail() != null) {
      return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(employee));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PostMapping("delete")
  public @ResponseBody ResponseEntity delete(@RequestBody @Valid String employeeEmail) {
    JSONObject jsonObject = new JSONObject(employeeEmail);
    String email = jsonObject.get("employee").toString();
    employeeService.deleteBy(email);
    return ResponseEntity.status(HttpStatus.OK).body("EMPLOYEE DELETED");
  }
}
