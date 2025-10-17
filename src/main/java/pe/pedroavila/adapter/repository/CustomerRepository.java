package pe.pedroavila.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.pedroavila.adapter.jpa.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
