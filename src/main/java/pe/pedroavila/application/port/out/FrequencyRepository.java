package pe.pedroavila.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.pedroavila.adapter.jpa.FrequencyEntity;

public interface FrequencyRepository extends JpaRepository<FrequencyEntity, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT COALESCE(MAX(f.code), 0) + 1 FROM frequencys f", nativeQuery = true)
    int generateCode();
}
