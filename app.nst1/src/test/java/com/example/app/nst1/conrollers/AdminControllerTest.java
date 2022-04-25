package com.example.app.nst1.conrollers;

import com.example.app.nst1.controller.AdminController;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.json.JSONObject;
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

import java.util.List;
import java.util.Optional;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private AdminService adminService;

  @Autowired private ObjectMapper objectMapper;

  private Admin admin;
  private List<Admin> admins;

  @BeforeEach
  public void setUp() {
    admin = new Admin("adminEmail", "adminPassword");
    admins = List.of(admin);
  }

  @Test
  public void loginTest() throws Exception {
    Mockito.when(adminService.login(admin.getAdminEmail(), admin.getAdminPassword()))
        .thenReturn(admin);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.adminEmail", Matchers.equalTo(admin.getAdminEmail())))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.adminPassword", Matchers.equalTo(admin.getAdminPassword())));
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
  public void findByTest() throws Exception {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("admin", admin.getAdminEmail());
    Optional<Admin> foundAdmin = Optional.of(admin);
    Mockito.when(adminService.findBy(admin.getAdminEmail())).thenReturn(foundAdmin);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admin/getBy/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.adminEmail", Matchers.equalTo(foundAdmin.get().getAdminEmail())))
        .andExpect(
            MockMvcResultMatchers.jsonPath(
                "$.adminPassword", Matchers.equalTo(foundAdmin.get().getAdminPassword())));
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
    adminService.deleteBy(admin.getAdminEmail());
    Mockito.verify(adminService, Mockito.times(1)).deleteBy(admin.getAdminEmail());
    Optional<Admin> result = adminService.findBy(admin.getAdminEmail());
    Assertions.assertEquals(result, Optional.empty());
  }
}
