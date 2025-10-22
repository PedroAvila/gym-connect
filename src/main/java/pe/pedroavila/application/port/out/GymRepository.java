package pe.pedroavila.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.pedroavila.domain.entity.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT COALESCE(MAX(g.code), 0) + 1 FROM gyms g", nativeQuery = true)
    int generateCode();

}
