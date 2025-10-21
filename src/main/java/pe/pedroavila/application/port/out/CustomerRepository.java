package pe.pedroavila.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.pedroavila.adapter.jpa.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT COALESCE(MAX(c.code), 0) + 1 FROM customers c", nativeQuery = true)
    int generateCode();

}
