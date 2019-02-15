package com.leo.bootcampglobant.repositories;

import java.util.List;
import java.util.Optional;

/**
 * Interface for a CRUD Repository
 * @param <T> Data type of the entity
 * @param <K> Data type of the main field of the entity
 */
public interface Repository<T, K> {

  List<T> readAll();
  Optional<T> readByKey(K key);
  T create(T entity);
  T update(T entity);
  boolean delete(T entity);
  boolean deleteByKey(K key);

}
