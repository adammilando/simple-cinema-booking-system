package org.livecodeJPA.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.livecodeJPA.Model.Film;

import java.util.List;

public class filmRepo extends Repo implements IRepo<Film> {
    public filmRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void add(Film film) {
        inTransaction(em -> em.persist(film));
    }

    @Override
    public void update(Film film) {
        inTransaction(em-> em.merge(film));
    }

    @Override
    public List<Film> findAll(Integer page, Integer pageSize) {
        TypedQuery<Film> query = entityManager.createQuery("SELECT f FROM Film f", Film.class);
        if (page != null && pageSize != null){
            query.setFirstResult((page -1) * pageSize);
            query.setMaxResults((pageSize));
        }
        return query.getResultList();
    }

    @Override
    public Film getById(Long id) {
        return entityManager.find(Film.class,id);
    }

    @Override
    public void delete(Long id) {
        inTransaction(em-> {Film film = getById(id);
        em.remove(film);});
    }
}
