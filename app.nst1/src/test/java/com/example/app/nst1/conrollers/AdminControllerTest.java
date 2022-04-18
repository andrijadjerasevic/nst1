package com.example.app.nst1.conrollers;

import com.example.app.nst1.controller.AdminController;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private AdminService adminService;

  @Autowired private ObjectMapper objectMapper;

  Admin admin;
  List<Admin> admins;

  @BeforeEach
  public void before() {
    admin = new Admin(1L, "a", "a");
    admins = Arrays.asList(admin);
  }

  @Test
  public void loginTest() throws Exception {
    Mockito.when(adminService.login(admin.getEmail(), admin.getPassword())).thenReturn(admin);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.equalTo(admin.getEmail())))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.password", Matchers.equalTo(admin.getPassword())));
  }

  @Test
  public void saveTest() throws Exception {
    Mockito.when(adminService.save(admin)).thenReturn(admin);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admin/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void findById() throws Exception {
    Optional<Admin> foundAdmin = Optional.of(admin);
    Mockito.when(adminService.findById(admin.getAdminId())).thenReturn(foundAdmin);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/admin/get/" + admin.getAdminId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.email", Matchers.equalTo(foundAdmin.get().getEmail())))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.password", Matchers.equalTo(foundAdmin.get().getPassword())));
  }

  @Test
  public void findAllTest() throws Exception {
    Mockito.when(adminService.findAll()).thenReturn(admins);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/admin/get/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admins)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateTest() throws Exception {
    Mockito.when(adminService.update(admin)).thenReturn(admin);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admin/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  public void deleteTest() throws Exception {
    adminService.delete(admin.getAdminId());
    Mockito.verify(adminService, Mockito.times(1)).delete(admin.getAdminId());
    Optional<Admin> result = adminService.findById(admin.getAdminId());
    Assertions.assertEquals(result, Optional.empty());
  }
}
