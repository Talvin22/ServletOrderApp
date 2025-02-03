package repository;

public interface BaseRepository<T> {

    void create(T obj);

    T getById(Long id);

    T update(T obj);

    void delete(Long id);

}
