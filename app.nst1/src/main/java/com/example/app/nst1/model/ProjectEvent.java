package com.example.app.nst1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projectEvent")
public class ProjectEvent implements Serializable {

  private static final long serialVersionUID = 1620883302536719732L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "projectEventId", unique = true)
  private String projectEventId;

  @NotNull
  @Column(name = "projectEventName")
  private String projectEventName;

  @NotNull
  @Column(name = "projectEventLocation")
  private String projectEventLocation;

  @Column(name = "projectEventDescription")
  private String projectEventDescription;

  @NotNull
  @Column(name = "startDate")
  private Date startDate;

  @NotNull
  @Column(name = "endDate")
  private Date endDate;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "adminEmail", referencedColumnName = "adminEmail")
  @NotNull
  @JsonIgnoreProperties({"adminPassword"})
  private Admin admin;

  @OneToMany
  @JoinTable(
      name = "participate",
      joinColumns = @JoinColumn(name = "projectEventId", referencedColumnName = "projectEventId"),
      inverseJoinColumns =
          @JoinColumn(name = "employeeEmail", referencedColumnName = "employeeEmail"))
  private List<Employee> employees;

  public ProjectEvent() {}

  public ProjectEvent(String projectEventId) {
    this.projectEventId = projectEventId;
  }

  public ProjectEvent(
      String projectEventId,
      String projectEventName,
      String projectEventLocation,
      String projectEventDescription,
      Date startDate,
      Date endDate) {
    this.projectEventId = projectEventId;
    this.projectEventName = projectEventName;
    this.projectEventLocation = projectEventLocation;
    this.projectEventDescription = projectEventDescription;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public ProjectEvent(
      String projectEventId,
      String projectEventName,
      String projectEventLocation,
      String projectEventDescription,
      Date startDate,
      Date endDate,
      Admin admin) {
    this.projectEventId = projectEventId;
    this.projectEventName = projectEventName;
    this.projectEventLocation = projectEventLocation;
    this.projectEventDescription = projectEventDescription;
    this.startDate = startDate;
    this.endDate = endDate;
    this.admin = admin;
  }

  public String getProjectEventId() {
    return projectEventId;
  }

  public void setProjectEventId(String projectEventId) {
    this.projectEventId = projectEventId;
  }

  public String getProjectEventName() {
    return projectEventName;
  }

  public void setProjectEventName(String projectEventName) {
    this.projectEventName = projectEventName;
  }

  public String getProjectEventLocation() {
    return projectEventLocation;
  }

  public void setProjectEventLocation(String projectEventLocation) {
    this.projectEventLocation = projectEventLocation;
  }

  public String getProjectEventDescription() {
    return projectEventDescription;
  }

  public void setProjectEventDescription(String projectEventDescription) {
    this.projectEventDescription = projectEventDescription;
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
    ProjectEvent projectEvent = (ProjectEvent) o;
    return projectEventId.equals(projectEvent.projectEventId)
        && projectEventName.equals(projectEvent.projectEventName)
        && projectEventLocation.equals(projectEvent.projectEventLocation)
        && projectEventDescription.equals(projectEvent.projectEventDescription)
        && startDate.equals(projectEvent.startDate)
        && endDate.equals(projectEvent.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        projectEventId,
        projectEventName,
        projectEventLocation,
        projectEventDescription,
        startDate,
        endDate);
  }

  @Override
  public String toString() {
    return "ProjectEvent{"
        + "projectEventId='"
        + projectEventId
        + '\''
        + ", projectEventName='"
        + projectEventName
        + '\''
        + ", projectEventLocation='"
        + projectEventLocation
        + '\''
        + ", projectEventDescription='"
        + projectEventDescription
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
