package com.example.app.nst1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

  private static final long serialVersionUID = -3292644387896154397L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "employeeEmail")
  private String employeeEmail;

  @NotNull
  @Column(name = "firstName")
  private String firstName;

  @NotNull
  @Column(name = "lastName")
  private String lastName;

  // TODO: 13-Apr-22 investigate this, when save return whole object with all values
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "adminEmail", referencedColumnName = "adminEmail")
  @NotNull
  @JsonIgnoreProperties({"adminPassword"})
  private Admin admin;

  @OneToMany
  @JoinTable(
      name = "participate",
      joinColumns = @JoinColumn(name = "employeeEmail", referencedColumnName = "employeeEmail"),
      inverseJoinColumns =
          @JoinColumn(name = "projectEventId", referencedColumnName = "projectEventId"))
  private List<ProjectEvent> projectEvents;

  public Employee() {}

  public Employee(String employeeEmail, String firstName, String lastName) {
    this.employeeEmail = employeeEmail;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Employee(String employeeEmail, String firstName, String lastName, Admin admin) {
    this.employeeEmail = employeeEmail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.admin = admin;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmployeeEmail() {
    return employeeEmail;
  }

  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }

  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return employeeEmail.equals(employee.employeeEmail)
        && firstName.equals(employee.firstName)
        && lastName.equals(employee.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employeeEmail, firstName, lastName);
  }

  @Override
  public String toString() {
    return "Employee{"
        + "employeeEmail='"
        + employeeEmail
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", admin="
        + admin
        + ", projectEvents="
        + projectEvents
        + '}';
  }
}
