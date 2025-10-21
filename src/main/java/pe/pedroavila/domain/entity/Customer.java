package pe.pedroavila.domain.entity;

import java.time.Instant;

import pe.pedroavila.adapter.common.EnumUtils;
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.UpdateCustomerCommand;
import pe.pedroavila.domain.enums.GenderCustomer;
import pe.pedroavila.domain.enums.StatusCustomer;

public record Customer(
                Long id,
                int code,
                String name,
                GenderCustomer gender,
                String phone,
                String email,
                Integer age,
                Instant createdAt,
                String observations,
                StatusCustomer status) {

        public Customer(CreateCustomer dto, int code) {
                this(
                                null,
                                code,
                                dto.name(),
                                EnumUtils.fromInt(GenderCustomer.class, dto.gender()),
                                dto.phone(),
                                dto.email(),
                                dto.age(),
                                Instant.now(),
                                dto.observations(),
                                StatusCustomer.ENABLED);
        }

        public Customer withUpdatedData(UpdateCustomerCommand dto) {
                return new Customer(
                                this.id,
                                this.code,

                                // Si el nombre está presente, úsalo, si no, usa el original
                                dto.name().orElse(this.name),

                                // Si el género está presente, mapea el enum, si no, usa el original
                                dto.gender()
                                                .map(g -> EnumUtils.fromInt(GenderCustomer.class, g))
                                                .orElse(this.gender),

                                dto.phone().orElse(this.phone),
                                dto.email().orElse(this.email),

                                // Atención: el campo 'age' en Customer es Integer, usa Optional.orElse
                                dto.age().orElse(this.age),

                                this.createdAt,
                                dto.observations().orElse(this.observations),

                                // Mapear Status
                                dto.status()
                                                .map(s -> EnumUtils.fromInt(StatusCustomer.class, s))
                                                .orElse(this.status));
        }
}
