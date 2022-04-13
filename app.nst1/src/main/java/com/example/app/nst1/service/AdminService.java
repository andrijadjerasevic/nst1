package com.example.app.nst1.service;

import com.example.app.nst1.controller.exceptions.AdminException;
import com.example.app.nst1.model.Admin;
import com.example.app.nst1.service.gemeric.GenericOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService extends GenericOperations<Admin, Long> {
  Admin login(String email, String password) throws AdminException;

  Admin save(Admin admin);

  Optional<Admin> findById(Long id);

  List<Admin> findAll();

  Admin update(Admin admin);

  void delete(Long id);
}
