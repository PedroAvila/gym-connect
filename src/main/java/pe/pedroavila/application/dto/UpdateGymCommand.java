package pe.pedroavila.application.dto;

import java.util.Optional;

import pe.pedroavila.adapter.common.ValidOptionalString;

public record UpdateGymCommand(
        @ValidOptionalString(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.") Optional<String> name,

        @ValidOptionalString(min = 3, max = 200, message = "La dirección debe tener entre 3 y 200 caracteres.") Optional<String> address,

        @ValidOptionalString(regexp = "^$|^[0-9]{9}$", message = "El teléfono debe tener exactamente 9 dígitos numéricos o estar vacío.") Optional<String> phone) {
}
