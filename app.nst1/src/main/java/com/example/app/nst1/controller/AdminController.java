package com.example.app.nst1.controller;

import com.example.app.nst1.exceptions.AdminException;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.service.AdminService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("admin")
public class AdminController {

  private AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping("login")
  public @ResponseBody ResponseEntity<Admin> login(@RequestBody @Validated Admin admin) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(adminService.login(admin.getAdminEmail(), admin.getAdminPassword()));
    } catch (AdminException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("save")
  public @ResponseBody ResponseEntity<Admin> save(@RequestBody @Valid Admin admin)
      throws AdminException {
    return ResponseEntity.status(HttpStatus.OK).body(adminService.save(admin));
  }

  @PostMapping("getBy/email")
  public @ResponseBody ResponseEntity<Admin> findBy(@RequestBody @Valid String adminEmail) {
    JSONObject jsonObject = new JSONObject(adminEmail);
    String email = jsonObject.get("admin").toString();
    Optional<Admin> foundAdmin = adminService.findBy(email);
    if (foundAdmin.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(foundAdmin.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @GetMapping("get/all")
  public @ResponseBody ResponseEntity<List<Admin>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
  }

  @PostMapping("update")
  public @ResponseBody ResponseEntity<Admin> update(@RequestBody @Valid Admin admin)
      throws AdminException {
    if (admin.getAdminEmail() != null) {
      return ResponseEntity.status(HttpStatus.OK).body(adminService.update(admin));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PostMapping("delete")
  public @ResponseBody ResponseEntity delete(@RequestBody @Valid String adminEmail) {
    JSONObject jsonObject = new JSONObject(adminEmail);
    String email = jsonObject.get("admin").toString();
    adminService.deleteBy(email);
    return ResponseEntity.status(HttpStatus.OK).body("ADMIN DELETED");
  }
}
