package com.lecaru.service;

import java.util.List;

public interface CrudService<T, D, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(D dto);
    T update(ID id, D dto);
    void delete(ID id);
}
