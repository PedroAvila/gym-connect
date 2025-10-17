package pe.pedroavila.application.port.out;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import pe.pedroavila.domain.entity.Customer;

public interface CustomerRepositoryPort {

    List<Customer> getAll();

    Customer create(Customer customer);

    int generateCode();

    boolean existsByName(Class<?> entityClass, String name);

    Optional<Customer> single(Long id);

    int update(Class<?> entityClass, Long id, Map<String, Object> fields);
}
