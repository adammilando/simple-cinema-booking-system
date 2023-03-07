package org.livecodeJPA.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.livecodeJPA.Model.Seat;
import org.livecodeJPA.Model.Theater;

import java.util.List;

public class seatRepo extends Repo implements IRepo<Seat>{
    public seatRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void add(Seat seat) {
        inTransaction(em -> {
            Theater theater = seat.getTheater();
            theater.setStock(theater.getStock()+1);
            em.persist(seat);
        });
    }

    @Override
    public void update(Seat seat) {
        inTransaction(em -> em.merge(seat));
    }

    @Override
    public List<Seat> findAll(Integer page, Integer pageSize) {
        TypedQuery<Seat> query = entityManager.createQuery("SELECT s FROM Seat s",Seat.class);
        if (page != null && pageSize != null){
            query.setFirstResult((page -1) * pageSize);
            query.setMaxResults((pageSize));
        }
        return query.getResultList();
    }

    @Override
    public Seat getById(Long id) {
        return entityManager.find(Seat.class, id);
    }

    @Override
    public void delete(Long id) {
        inTransaction(em ->{
            Seat seat = getById(id);
            if (seat != null){
                Theater theater = seat.getTheater();
                if (theater.getStock() > 0){
                    theater.setStock((theater.getStock()-1));
                    em.remove(seat);
                }else {
                    throw  new RuntimeException("Seat Stock tidak bisa negatif");
                }
            }
        });
    }
}
