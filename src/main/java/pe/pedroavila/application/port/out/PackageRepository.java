package pe.pedroavila.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackageRepository extends JpaRepository<pe.pedroavila.domain.entity.Package, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT COALESCE(MAX(p.code), 0) + 1 FROM packages p", nativeQuery = true)
    int generateCode();

}
