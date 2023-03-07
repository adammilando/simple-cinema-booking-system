package org.livecodeJPA.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.livecodeJPA.Model.Theater;

import java.util.List;

public class theaterRepo extends Repo implements IRepo<Theater> {
    public theaterRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void add(Theater theater) {
        inTransaction(em-> em.persist(theater));
    }

    @Override
    public void update(Theater theater) {
        inTransaction(em-> em.merge(theater));
    }

    @Override
    public List<Theater> findAll(Integer page, Integer pageSize) {
        TypedQuery<Theater> query = entityManager.createQuery("SELECT t FROM Theater t", Theater.class);
        if (page != null && pageSize != null){
            query.setFirstResult((page -1) * pageSize);
            query.setMaxResults((pageSize));
        }
        return query.getResultList();
    }

    @Override
    public Theater getById(Long id) {
        return entityManager.find(Theater.class,id);
    }

    @Override
    public void delete(Long id) {
        inTransaction(em-> {Theater theater = getById(id);
        em.remove(theater);});
    }
}
