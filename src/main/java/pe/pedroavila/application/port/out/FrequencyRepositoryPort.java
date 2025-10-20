package pe.pedroavila.application.port.out;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import pe.pedroavila.domain.entity.Frequency;

public interface FrequencyRepositoryPort {

    List<Frequency> getAll();

    Frequency create(Frequency frequency);

    int generateCode();

    boolean existsByName(Class<?> entityClass, String name);

    Optional<Frequency> single(Long id);

    int update(Class<?> entityClass, Long id, Map<String, Object> fields);

}
