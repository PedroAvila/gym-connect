package pe.pedroavila.application.dto;

import java.util.Optional;

import pe.pedroavila.adapter.common.ValidOptionalString;

public record UpdateFrequencyCommand(
        @ValidOptionalString(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.") Optional<String> name,
        Optional<Integer> duration) {

}
