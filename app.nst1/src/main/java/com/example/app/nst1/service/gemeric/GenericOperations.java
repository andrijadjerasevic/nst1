package com.example.app.nst1.service.gemeric;

import java.util.List;
import java.util.Optional;

public interface GenericOperations<T, K> {
  T save(T t);

  Optional<T> findById(K k);

  List<T> findAll();

  T update(T t);

  void delete(K k);
}
