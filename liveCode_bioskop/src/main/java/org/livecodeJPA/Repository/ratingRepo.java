package org.livecodeJPA.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.livecodeJPA.Model.Rating;

import java.util.List;

public class ratingRepo extends Repo implements IRepo<Rating> {
    public ratingRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void add(Rating rating) {
        inTransaction(em -> em.persist(rating));
    }

    @Override
    public void update(Rating rating) {
        inTransaction(em -> em.merge(rating));
    }

    @Override
    public List<Rating> findAll(Integer page, Integer pageSize) {
        TypedQuery<Rating> query = entityManager.createQuery("SELECT r FROM Rating r", Rating.class);
        if (page != null && pageSize != null){
            query.setFirstResult((page -1) * pageSize);
            query.setMaxResults((pageSize));
        }
        return query.getResultList();
    }

    @Override
    public Rating getById(Long id) {
        return entityManager.find(Rating.class, id);
    }

    @Override
    public void delete(Long id) {
        inTransaction(em -> {Rating rating = getById(id);
        em.remove(rating);});
    }
}
