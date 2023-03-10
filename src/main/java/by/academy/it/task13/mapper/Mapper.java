package by.academy.it.task13.mapper;

public interface Mapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);
}
