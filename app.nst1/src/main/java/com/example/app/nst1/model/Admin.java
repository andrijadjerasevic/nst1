package com.example.app.nst1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "admin")
@XmlRootElement
public class Admin implements Serializable {

  private static final long serialVersionUID = -8026000654567660851L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @NotNull
  @Column(name = "adminId")
  private Long adminId;

  @NotNull
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "password")
  private String password;

  public Admin() {}

  public Admin(Long adminId) {
    this.adminId = adminId;
  }

  public Admin(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public Admin(Long adminId, String email, String password) {
    this.adminId = adminId;
    this.email = email;
    this.password = password;
  }

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Admin admin = (Admin) o;
    return adminId == admin.adminId && email.equals(admin.email) && password.equals(admin.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminId, email, password);
  }

  @Override
  public String toString() {
    return "Admin{"
        + "adminId="
        + adminId
        + ", email='"
        + email
        + '\''
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
