package org.livecodeJPA.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.livecodeJPA.Model.Customer;

import java.util.List;

public class customerRepo extends Repo implements IRepo<Customer> {

    public customerRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void add(Customer customer) {
        inTransaction((em) -> em.persist(customer));
    }

    @Override
    public void update(Customer customer) {
        inTransaction((em) -> em.merge(customer));
    }

    @Override
    public List<Customer> findAll(Integer page, Integer pageSize) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        if (page != null && pageSize != null){
            query.setFirstResult((page -1) * pageSize);
            query.setMaxResults((pageSize));
        }
        return query.getResultList();
    }

    @Override
    public Customer getById(Long id) {
        return entityManager.find(Customer.class,id);
    }

    @Override
    public void delete(Long id) {
        inTransaction(em ->{Customer customer = getById(id);
        em.remove(customer);});
    }
}
