package pe.pedroavila.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.pedroavila.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT COALESCE(MAX(c.code), 0) + 1 FROM customers c", nativeQuery = true)
    int generateCode();

}
