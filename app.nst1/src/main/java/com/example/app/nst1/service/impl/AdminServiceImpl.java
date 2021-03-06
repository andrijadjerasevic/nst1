package com.example.app.nst1.service.impl;

import com.example.app.nst1.exceptions.AdminException;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.repository.AdminRepository;
import com.example.app.nst1.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

  private AdminRepository adminRepository;

  public AdminServiceImpl(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @Override
  public Admin save(Admin admin) throws AdminException {
    if (adminRepository.findByEmail(admin.getAdminEmail()).isPresent()) {
      throw new AdminException("Admin already exists!");
    }
    return adminRepository.save(admin);
  }

  @Override
  public Admin login(String adminEmail, String adminPassword) throws AdminException {
    Admin admin = adminRepository.login(adminEmail, adminPassword);
    if (admin == null) {
      throw new AdminException("Wrong email or password");
    }
    return admin;
  }

  @Override
  public Optional<Admin> findBy(String adminEmail) {
    return adminRepository.findByEmail(adminEmail);
  }

  @Override
  public List<Admin> findAll() {
    return adminRepository.findAll();
  }

  @Override
  @Transactional
  public Admin update(Admin admin) throws AdminException {
    // only admin password could be updated, because admin email is primary key
    adminRepository.updateAdminPassword(admin.getAdminEmail(), admin.getAdminPassword());
    Optional<Admin> updatedAdmin = adminRepository.findByEmail(admin.getAdminEmail());
    if (updatedAdmin.isPresent()) {
      return updatedAdmin.get();
    }
    throw new AdminException("Error during admin update! Admin must exists in order to be updated!");
  }

  @Override
  @Transactional
  public void deleteBy(String email) {
    adminRepository.deleteByEmail(email);
  }
}
