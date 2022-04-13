package com.example.app.nst1.service.impl;

import com.example.app.nst1.controller.exceptions.AdminException;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.repository.AdminRepository;
import com.example.app.nst1.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

  private AdminRepository adminRepository;

  public AdminServiceImpl(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }

  @Override
  public Admin save(Admin admin) {
    return adminRepository.save(admin);
  }

  @Override
  public Admin login(String email, String password) throws AdminException {
    Admin admin = adminRepository.login(email, password);
    if (admin == null) {
      throw new AdminException("Wrong email or password");
    }
    return admin;
  }

  @Override
  public Optional<Admin> findById(Long id) {
    return adminRepository.findById(id);
  }

  @Override
  public List<Admin> findAll() {
    return adminRepository.findAll();
  }

  @Override
  public Admin update(Admin admin) {
    return adminRepository.save(admin);
  }

  @Override
  public void delete(Long id) {
    adminRepository.deleteById(id);
  }
}
