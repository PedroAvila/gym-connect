package pe.pedroavila.adapter.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pe.pedroavila.adapter.jpa.FrequencyEntity;
import pe.pedroavila.application.port.out.FrequencyRepositoryPort;
import pe.pedroavila.domain.entity.Frequency;

@Repository
public class FrequencyRepositoryAdapter implements FrequencyRepositoryPort {

    private final FrequencyRepository frequencyRepository;
    private final EntityDatabaseHelper entityDatabaseHelper;

    public FrequencyRepositoryAdapter(FrequencyRepository frequencyRepository,
            EntityDatabaseHelper entityDatabaseHelper) {
        this.frequencyRepository = frequencyRepository;
        this.entityDatabaseHelper = entityDatabaseHelper;
    }

    @Override
    public List<Frequency> getAll() {
        var listFrequencyEntity = this.frequencyRepository.findAll();
        return listFrequencyEntity.stream()
                .map(e -> new Frequency(e.getId(), e.getCode(), e.getName(), e.getDuration())).toList();
    }

    @Override
    public Frequency create(Frequency frequency) {
        var frequencyEntity = new FrequencyEntity(null, frequency.code(), frequency.name(), frequency.duration());
        var entity = this.frequencyRepository.save(frequencyEntity);
        return new Frequency(entity.getId(), entity.getCode(), entity.getName(), entity.getDuration());
    }

    @Override
    public int generateCode() {
        return this.entityDatabaseHelper.generateCode(FrequencyEntity.class);
    }

    @Override
    public boolean existsByName(Class<?> entityClass, String name) {
        return this.entityDatabaseHelper.existsByName(entityClass, name);
    }

    @Override
    public Optional<Frequency> single(Long id) {
        return this.frequencyRepository.findById(id)
                .map(e -> new Frequency(e.getId(), e.getCode(), e.getName(), e.getDuration()));
    }

    @Override
    public int update(Class<?> entityClass, Long id, Map<String, Object> fields) {
        return this.entityDatabaseHelper.update(entityClass, id, fields);
    }
}
