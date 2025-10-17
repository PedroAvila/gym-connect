package pe.pedroavila.adapter.repository;

// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

import module java.base;

import org.springframework.stereotype.Repository;

import pe.pedroavila.adapter.jpa.GymEntity;
import pe.pedroavila.application.port.out.GymRepositoryPort;
import pe.pedroavila.domain.entity.Gym;

@Repository
public class GymRepositoryAdapter implements GymRepositoryPort {

    private final GymRepository gymRepository;
    private final EntityDatabaseHelper entityDatabaseHelper;

    public GymRepositoryAdapter(GymRepository gymRepository, EntityDatabaseHelper entityDatabaseHelper) {
        this.gymRepository = gymRepository;
        this.entityDatabaseHelper = entityDatabaseHelper;
    }

    @Override
    public Gym create(Gym gym) {
        GymEntity gymEntity = new GymEntity(null, gym.code(), gym.name(), gym.address(), gym.phone(), gym.createdAt());
        var entity = this.gymRepository.save(gymEntity);
        return new Gym(entity.getId(), entity.getCode(), entity.getName(), entity.getAddress(), entity.getPhone(),
                entity.getCreatedAt());
    }

    @Override
    public int generateCode() {
        return entityDatabaseHelper.generateCode(GymEntity.class);
    }

    @Override
    public boolean existsByName(Class<?> entityClass, String name) {
        return entityDatabaseHelper.existsByName(entityClass, name);
    }

    @Override
    public List<Gym> getAll() {
        var listGymEntity = this.gymRepository.findAll();
        var listGyms = listGymEntity.stream()
                .map(g -> new Gym(g.getId(), g.getCode(), g.getName(), g.getAddress(), g.getPhone(), g.getCreatedAt()));
        return listGyms.toList();
    }

    @Override
    public Optional<Gym> single(Long id) {
        return this.gymRepository.findById(id)
                .map(e -> new Gym(e.getId(), e.getCode(), e.getName(), e.getAddress(), e.getPhone(), e.getCreatedAt()));
    }

    @Override
    public int update(Class<?> entityClass, Long id, Map<String, Object> fields) {
        return entityDatabaseHelper.update(entityClass, id, fields);
    }
}
