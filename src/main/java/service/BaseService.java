package service;

import java.util.Optional;

public interface BaseService<T> {
    void create(T obj);

    Optional<T> getById(Long id);

    T update(Long id, T obj);

    void delete(Long id);

}
