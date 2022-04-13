package com.example.app.nst1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project implements Serializable {

  private static final long serialVersionUID = 1620883302536719732L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @NotNull
  @Column(name = "projectId")
  private Long projectId;

  @Column(name = "projectName")
  private String projectName;

  @Column(name = "startDate")
  private Date startDate;

  @Column(name = "endDate")
  private Date endDate;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "adminId", referencedColumnName = "adminId")
  @NotNull
  private Admin admin;

  @OneToMany
  @JoinTable(
      name = "participate",
      joinColumns = @JoinColumn(name = "projectId", referencedColumnName = "projectId"),
      inverseJoinColumns = @JoinColumn(name = "employeeId", referencedColumnName = "employeeId"))
  private List<Employee> employees;

  public Project() {}

  public Project(Long projectId) {
    this.projectId = projectId;
  }

  public Project(Long projectId, String projectName, Date startDate, Date endDate) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Project(Long projectId, String projectName, Date startDate, Date endDate, Admin admin) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.admin = admin;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Project project = (Project) o;
    return projectId == project.projectId
        && projectName.equals(project.projectName)
        && startDate.equals(project.startDate)
        && endDate.equals(project.endDate)
        && admin.equals(project.admin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, projectName, startDate, endDate, admin);
  }

  @Override
  public String toString() {
    return "Project{"
        + "projectId="
        + projectId
        + ", projectName='"
        + projectName
        + '\''
        + ", startDate="
        + startDate
        + ", endDate="
        + endDate
        + ", admin="
        + admin
        + ", employees="
        + employees
        + '}';
  }
}
