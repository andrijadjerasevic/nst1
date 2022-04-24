package com.example.app.nst1.service;

import com.example.app.nst1.model.Admin;
import com.example.app.nst1.repository.AdminRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceTest {

  @Autowired private AdminService adminService;

  @MockBean private AdminRepository adminRepository;

  private Admin expectedAdmin;
  private List<Admin> expectedAdmins;

  @BeforeAll
  public void beforeAll() {
    expectedAdmin = generateAdmin();
    expectedAdmins = Arrays.asList(expectedAdmin);
  }

  @Test
  public void loginTest() throws Exception {
    Mockito.when(
            adminRepository.login(expectedAdmin.getAdminEmail(), expectedAdmin.getAdminPassword()))
        .thenReturn(expectedAdmin);
    Admin loggedAdmin =
        adminService.login(expectedAdmin.getAdminEmail(), expectedAdmin.getAdminPassword());
    Assertions.assertNotNull(loggedAdmin);
    Assertions.assertEquals(expectedAdmin, loggedAdmin);
  }

  @Test
  public void saveTest() {
    Mockito.when(adminRepository.save(expectedAdmin)).thenReturn(expectedAdmin);
    Admin savedAdmin = adminService.save(expectedAdmin);
    Assertions.assertNotNull(savedAdmin);
    Assertions.assertEquals(expectedAdmin, savedAdmin);
  }

  @Test
  public void findByTest() {
    Mockito.when(adminRepository.findByEmail(expectedAdmin.getAdminEmail()))
        .thenReturn(Optional.of(expectedAdmin));
    Optional<Admin> foundAdmin = adminService.findBy(expectedAdmin.getAdminEmail());
    Assertions.assertTrue(foundAdmin.isPresent());
    Assertions.assertEquals(expectedAdmin, foundAdmin.get());
  }

  @Test
  public void findAllTest() {
    Mockito.when(adminRepository.findAll()).thenReturn(expectedAdmins);
    List<Admin> foundAdmins = adminService.findAll();
    Assertions.assertTrue(foundAdmins != null && !foundAdmins.isEmpty());
    Assertions.assertTrue(foundAdmins.contains(expectedAdmin));
  }

  @Test
  public void updateTest() {
    Optional<Admin> admin = Optional.of(generateAdmin());
    Admin updatedAdmin = new Admin();
    updatedAdmin.setAdminEmail(admin.get().getAdminEmail());
    updatedAdmin.setAdminPassword(RandomStringUtils.randomAlphabetic(5));

    Mockito.when(adminRepository.findByEmail(admin.get().getAdminEmail()))
        .thenReturn(Optional.of(updatedAdmin));

    adminRepository.updateAdminPassword(
        updatedAdmin.getAdminEmail(), updatedAdmin.getAdminPassword());

    Mockito.verify(adminRepository, Mockito.times(1))
        .updateAdminPassword(admin.get().getAdminEmail(), updatedAdmin.getAdminPassword());

    Optional<Admin> foundAdmin = adminRepository.findByEmail(admin.get().getAdminEmail());

    Assertions.assertTrue(foundAdmin.isPresent());
    Assertions.assertEquals(updatedAdmin, foundAdmin.get());
  }

  @Test
  public void deleteTest() {
    Mockito.doNothing().when(adminRepository).deleteByEmail(expectedAdmin.getAdminEmail());
    adminService.deleteBy(expectedAdmin.getAdminEmail());
    Mockito.verify(adminRepository, Mockito.times(1)).deleteByEmail(expectedAdmin.getAdminEmail());
  }

  private Admin generateAdmin() {
    return new Admin(
        RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
  }
}
