package pe.pedroavila.domain.entity;

import java.time.Instant;

import pe.pedroavila.application.dto.CreateGym;
import pe.pedroavila.application.dto.UpdateGymCommand;

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

    public Gym withUpdatedData(UpdateGymCommand dto) {
        return new Gym(
                this.id, // Se mantiene el ID original
                this.code, // Se mantiene el código (inmutable)

                // Si 'name' está presente, usa el nuevo; si no, usa el actual
                dto.name().orElse(this.name),

                // Si 'address' está presente, usa el nuevo; si no, usa el actual
                dto.address().orElse(this.address),

                // Si 'phone' está presente, usa el nuevo; si no, usa el actual
                dto.phone().orElse(this.phone),

                this.createdAt // Se mantiene la fecha de creación (inmutable)
        );
    }

}
