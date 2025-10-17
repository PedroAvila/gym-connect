package pe.pedroavila.adapter.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pe.pedroavila.adapter.jpa.CustomerEntity;
import pe.pedroavila.adapter.repository.converter.EnumUtils;
import pe.pedroavila.application.port.out.CustomerRepositoryPort;
import pe.pedroavila.domain.entity.Customer;
import pe.pedroavila.domain.enums.GenderCustomer;
import pe.pedroavila.domain.enums.StatusCustomer;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;
    private final EntityDatabaseHelper entityDatabaseHelper;

    public CustomerRepositoryAdapter(CustomerRepository customerRepository, EntityDatabaseHelper entityDatabaseHelper) {
        this.customerRepository = customerRepository;
        this.entityDatabaseHelper = entityDatabaseHelper;
    }

    @Override
    public List<Customer> getAll() {
        var listCustomerEntity = this.customerRepository.findAll();
        return listCustomerEntity.stream()
                .map(e -> new Customer(e.getId(), e.getCode(), e.getName(),
                        EnumUtils.fromInt(GenderCustomer.class, e.getGender()), e.getPhone(), e.getEmail(),
                        e.getAge(), e.getCreatedAt(), e.getObservations(),
                        EnumUtils.fromInt(StatusCustomer.class, e.getStatus())))
                .toList();
    }

    @Override
    public Customer create(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity(null, customer.code(), customer.name(),
                customer.gender().getValue(),
                customer.phone(), customer.email(), customer.age(), customer.createdAt(), customer.observations(),
                customer.status().getValue());
        var entity = this.customerRepository.save(customerEntity);
        return new Customer(entity.getId(), entity.getCode(), entity.getName(),
                EnumUtils.fromInt(GenderCustomer.class, entity.getGender()), entity.getPhone(),
                entity.getEmail(), entity.getAge(), entity.getCreatedAt(), entity.getObservations(),
                EnumUtils.fromInt(StatusCustomer.class, entity.getStatus()));
    }

    @Override
    public int generateCode() {
        return this.entityDatabaseHelper.generateCode(CustomerEntity.class);
    }

    @Override
    public boolean existsByName(Class<?> entityClass, String name) {
        return this.entityDatabaseHelper.existsByName(entityClass, name);
    }

    @Override
    public Optional<Customer> single(Long id) {
        return this.customerRepository.findById(id)
                .map(e -> new Customer(e.getId(), e.getCode(), e.getName(), EnumUtils.fromInt(GenderCustomer.class,
                        e.getGender()), e.getPhone(), e.getEmail(),
                        e.getAge(), e.getCreatedAt(), e.getObservations(),
                        EnumUtils.fromInt(StatusCustomer.class, e.getStatus())));
    }

    @Override
    public int update(Class<?> entityClass, Long id, Map<String, Object> fields) {
        return this.entityDatabaseHelper.update(entityClass, id, fields);
    }
}
