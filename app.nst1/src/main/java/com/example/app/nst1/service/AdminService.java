package com.example.app.nst1.service;

import com.example.app.nst1.exceptions.AdminException;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.service.genericOperationService.GenericOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService extends GenericOperationsService<Admin, String> {
  Admin login(String adminEmail, String adminPassword) throws AdminException;

  Admin save(Admin admin) throws AdminException;

  Optional<Admin> findBy(String adminEmail);

  List<Admin> findAll();

  Admin update(Admin admin) throws AdminException;

  void deleteBy(String email);
}
