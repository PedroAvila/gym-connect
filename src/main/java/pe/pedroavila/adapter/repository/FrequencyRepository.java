package pe.pedroavila.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.pedroavila.adapter.jpa.FrequencyEntity;

public interface FrequencyRepository extends JpaRepository<FrequencyEntity, Long> {

}
