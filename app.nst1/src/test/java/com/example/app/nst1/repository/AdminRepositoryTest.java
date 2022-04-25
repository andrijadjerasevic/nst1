package com.example.app.nst1.repository;

import com.example.app.nst1.model.Admin;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AdminRepositoryTest {

  @Autowired private AdminRepository adminRepository;

  private Admin expectedAdmin;
  private List<Admin> expectedAdmins;

  @BeforeAll
  public void setUp() {
    expectedAdmin = generateAdmin();
    expectedAdmins = List.of(expectedAdmin);
    adminRepository.save(expectedAdmin);
  }

  @Test
  public void loginTest() {
    Admin foundAdmin =
        adminRepository.login(expectedAdmin.getAdminEmail(), expectedAdmin.getAdminPassword());
    Assertions.assertNotNull(foundAdmin);
    Assertions.assertEquals(expectedAdmin, foundAdmin);
  }

  @Test
  public void saveTest() {
    Admin expectedAdmin = generateAdmin();
    Admin savedAdmin = adminRepository.save(expectedAdmin);
    Assertions.assertNotNull(savedAdmin);
    Assertions.assertEquals(expectedAdmin, savedAdmin);
  }

  @Test
  public void findByTest() {
    Optional<Admin> foundAdmin = adminRepository.findByEmail(expectedAdmin.getAdminEmail());
    Assertions.assertTrue(foundAdmin.isPresent());
    Assertions.assertEquals(expectedAdmin, foundAdmin.get());
  }

  @Test
  public void findAllTest() {
    List<Admin> foundAdmins = adminRepository.findAll();
    Assertions.assertNotNull(foundAdmins);
    Assertions.assertFalse(foundAdmins.isEmpty());
    Assertions.assertTrue(foundAdmins.contains(expectedAdmin));
  }

  @Test
  @Transactional
  public void updateTest() {
    String adminPassword = generateAdmin().getAdminPassword();
    adminRepository.updateAdminPassword(expectedAdmin.getAdminEmail(), adminPassword);
    Optional<Admin> updatedAdmin = adminRepository.findByEmail(expectedAdmin.getAdminEmail());

    Assertions.assertTrue(updatedAdmin.isPresent());
    Assertions.assertEquals(updatedAdmin.get().getAdminPassword(), adminPassword);
  }

  @Test
  @Transactional
  public void deleteTest() {
    adminRepository.deleteByEmail(expectedAdmin.getAdminEmail());
    Optional<Admin> deletedAdmin = adminRepository.findByEmail(expectedAdmin.getAdminEmail());
    Assertions.assertFalse(deletedAdmin.isPresent());
  }

  private Admin generateAdmin() {
    return new Admin(
        RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
  }
}
