package pe.pedroavila.domain.entity;

import java.time.Instant;

import pe.pedroavila.application.dto.CreateGym;

public record Gym(
        Long id,
        int code,
        String name,
        String address,
        String phone,
        Instant createdAt) {

    public Gym(CreateGym dto, int code) {
        this(null, code, dto.name(), dto.address(), dto.phone(), Instant.now());
    }

}
