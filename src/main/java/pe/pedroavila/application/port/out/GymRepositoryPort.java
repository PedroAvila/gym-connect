package pe.pedroavila.application.port.out;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import pe.pedroavila.domain.entity.Gym;

public interface GymRepositoryPort {

    List<Gym> getAll();

    Gym create(Gym gym);

    int generateCode();

    boolean existsByName(Class<?> entityClass, String name);

    Optional<Gym> single(Long id);

    int update(Class<?> entityClass, Long id, Map<String, Object> fields);
}
