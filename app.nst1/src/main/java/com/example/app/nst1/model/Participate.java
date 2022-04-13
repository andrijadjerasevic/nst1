package com.example.app.nst1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "participate")
public class Participate implements Serializable {

  private static final long serialVersionUID = -3407039894690255845L;

  @Id
  @Column(name = "employeeId")
  private Employee employee;

  @Id
  @Column(name = "projectId")
  private Project project;

  public Participate() {}

  public Participate(Employee employee, Project project) {
    this.employee = employee;
    this.project = project;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Participate that = (Participate) o;
    return employee.equals(that.employee) && project.equals(that.project);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employee, project);
  }

  @Override
  public String toString() {
    return "Participate{" + "employee=" + employee + ", project=" + project + '}';
  }
}
