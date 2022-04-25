package com.example.app.nst1.service.genericOperationService;

import java.util.List;
import java.util.Optional;

public interface GenericOperationsService<T, K> {
  T save(T t) throws Exception;

  Optional<T> findBy(K k) throws Exception;

  List<T> findAll() throws Exception;

  T update(T t) throws Exception;

  void deleteBy(K k) throws Exception;
}
