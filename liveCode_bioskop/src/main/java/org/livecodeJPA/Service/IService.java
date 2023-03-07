package org.livecodeJPA.Service;

import java.util.List;

public interface IService<T> {
    void add(T t);
    void update(T t);
    List<T> findAll(Integer page, Integer pageSize);
    T getById(Long id);
    void delete(Long id);
}
