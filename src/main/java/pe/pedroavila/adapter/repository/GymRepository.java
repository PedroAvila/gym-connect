package pe.pedroavila.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.pedroavila.adapter.jpa.GymEntity;

public interface GymRepository extends JpaRepository<GymEntity, Long> {

}
