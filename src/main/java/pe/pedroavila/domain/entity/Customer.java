package pe.pedroavila.domain.entity;

import java.time.Instant;

import pe.pedroavila.adapter.common.EnumUtils;
import pe.pedroavila.application.dto.CreateCustomer;
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
}
