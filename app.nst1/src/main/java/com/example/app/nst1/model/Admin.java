package com.example.app.nst1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {

  private static final long serialVersionUID = -8026000654567660851L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "adminEmail", unique = true)
  private String adminEmail;

  @NotNull
  @Column(name = "adminPassword", unique = true)
  @JsonIgnoreProperties({"adminPassword"})
  private String adminPassword;

  public Admin() {}

  public Admin(String adminEmail, String adminPassword) {
    this.adminEmail = adminEmail;
    this.adminPassword = adminPassword;
  }

  public String getAdminEmail() {
    return adminEmail;
  }

  public void setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
  }

  public String getAdminPassword() {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Admin admin = (Admin) o;
    return adminEmail.equals(admin.adminEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminEmail);
  }

  @Override
  public String toString() {
    return "Admin{"
        + "adminEmail='"
        + adminEmail
        + '\''
        + ", adminPassword='"
        + adminPassword
        + '\''
        + '}';
  }
}
