package com.albert.dao;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import com.albert.model.BaseEntity;
import io.quarkus.logging.Log;


public abstract class BaseDAO<T extends BaseEntity> {

  private EntityManager entityManager;

  private Class<T> entityClass;

  @Transactional
  public void delete(UUID id) {
    T entity = entityManager.getReference(entityClass, id);
    entityManager.remove(entity);
  }

  @Transactional
  public T findById(UUID id) {
    T t = entityManager.find(entityClass, id);
    return t;
  }

  @Transactional
  public List<T> getAll() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
    Root<T> root = criteriaQuery.from(entityClass);
    CriteriaQuery<T> select = criteriaQuery.select(root);
    TypedQuery<T> typedQuery = entityManager.createQuery(select);
    return typedQuery.getResultList();
  }

  public Class<T> getEntityClass() {
    return entityClass;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  @Transactional
  public void save(T entity) {
    if (entityManager == null) {
      Log.info("entity manager null");
    }
    if (entity.getId() == null) {
      entity.setId(UUID.randomUUID());
    }
    entityManager.persist(entity);
  }

  protected void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  protected void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public void update(UUID id, T entity) {
    T t = entityManager.find(entityClass, id);
    updateEntity(t, entity);
  }

  protected abstract void updateEntity(T t, T entity);
}
