package br.org.aappe.erp.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;

/**
 * @author Phelipe Melanias
 */
public abstract class Dao<T> {
    private final Class<T> classe;
    protected final EntityManager manager;

    @SuppressWarnings("unchecked")
    public Dao(EntityManager manager) {
        this.manager = manager;
        this.classe  = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T merge(T o) {
        try {
            return manager.merge(o);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public T find(int id) {
        try {
            return manager.find(classe, id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public T find(long id) {
        try {
            return manager.find(classe, id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void remove(T o) {
        try {
            manager.remove(o);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void persist(T o) {
        try {
            manager.persist(o);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void refresh(T o) {
        try {
            manager.refresh(o);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public List<T> listAll() {
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<T> c = cb.createQuery(classe);

            return manager.createQuery(c).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public List<T> listAllById() {
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<T> c = cb.createQuery(classe);

            Root<T> root = c.from(classe);

            return manager.createQuery(c.orderBy(cb.asc(root.get("id")))).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public List<T> listAllByIdDesc() {
        try {
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<T> c = cb.createQuery(classe);

            Root<T> root = c.from(classe);

            return manager.createQuery(c.orderBy(cb.desc(root.get("id")))).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}