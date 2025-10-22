package pe.pedroavila.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.pedroavila.domain.entity.Frequency;

public interface FrequencyRepository extends JpaRepository<Frequency, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT COALESCE(MAX(f.code), 0) + 1 FROM frequencys f", nativeQuery = true)
    int generateCode();
}
