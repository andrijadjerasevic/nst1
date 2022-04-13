package com.example.app.nst1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

  private static final long serialVersionUID = -3292644387896154397L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @NotNull
  @Column(name = "employeeId")
  private Long employeeId;

  @NotNull
  @Column(name = "firstName")
  private String firstName;

  @NotNull
  @Column(name = "lastName")
  private String lastName;

  @NotNull
  @Column(name = "email")
  private String email;

  // TODO: 13-Apr-22 investigaate this, when save return whole object with all values
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "adminId", referencedColumnName = "adminId")
  private Admin admin;

  public Employee() {}

  public Employee(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Employee(Long employeeId, String firstName, String lastName, String email) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Employee(Long employeeId, String firstName, String lastName, String email, Admin admin) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.admin = admin;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    return employeeId == employee.employeeId
        && firstName.equals(employee.firstName)
        && lastName.equals(employee.lastName)
        && email.equals(employee.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employeeId, firstName, lastName, email);
  }

  @Override
  public String toString() {
    return "Employee{"
        + "employeeId="
        + employeeId
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
