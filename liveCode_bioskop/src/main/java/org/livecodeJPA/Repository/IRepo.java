package org.livecodeJPA.Repository;

import java.util.List;

public interface IRepo<T> {
    void add(T t);
    void update(T t);
    List<T> findAll(Integer page, Integer pageSize);
    T getById(Long id);
    void delete(Long id);
}
