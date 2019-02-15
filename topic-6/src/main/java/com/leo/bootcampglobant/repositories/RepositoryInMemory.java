package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.WithId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public abstract class RepositoryInMemory<T extends WithId> implements Repository<T, Long> {
  protected List<T> entities;
  private static final AtomicLong ID_GENERATOR = new AtomicLong();

  protected RepositoryInMemory(List<T> entities) {
    this.entities = entities;
  }

  protected RepositoryInMemory() {
    this(new ArrayList<>());
  }

  @Override
  public List<T> readAll() {
    return Collections.unmodifiableList(entities);
  }

  @Override
  public Optional<T> readByKey(Long key) {
    return entities.stream().filter(e -> e.getId() == key).reduce((a, b) -> null);
  }

  @Override
  public T create(T entity) {
    entity.setId(ID_GENERATOR.incrementAndGet());
    entities.add(entity);
    return entity;
  }

  @Override
  public T update(T entity) {
    entities.removeIf(e -> e.getId() == entity.getId());
    entities.add(entity);
    return entity;
  }

  @Override
  public boolean delete(T entity) {
    return entities.remove(entity);
  }

  @Override
  public boolean deleteByKey(Long key) {
    return entities.removeIf(e -> e.getId() == key);
  }
}
