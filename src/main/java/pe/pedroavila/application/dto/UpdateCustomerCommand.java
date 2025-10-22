package pe.pedroavila.application.dto;

import java.util.Optional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import pe.pedroavila.adapter.common.ValidOptionalString;

public record UpdateCustomerCommand(
        @ValidOptionalString(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.") Optional<String> name,

        @Min(value = 1, message = "El valor de género debe ser 1 (MALE) o 2 (FEMALE)") @Max(value = 2, message = "El valor de género debe ser 1 (MALE) o 2 (FEMALE)") Optional<Integer> gender,

        @ValidOptionalString(regexp = "^$|^[0-9]{9}$", message = "El teléfono debe tener exactamente 9 dígitos numéricos o estar vacío.") Optional<String> phone,

        @Email(message = "El formato del email es inválido") @Size(max = 255, message = "El email no debe exceder los 255 caracteres") Optional<String> email,

        @Min(value = 1, message = "La edad debe ser mayor a 0") Optional<Integer> age,

        Optional<String> observations,

        @Min(value = 0, message = "El estado debe ser 0 o 1") @Max(value = 1, message = "El estado debe ser 0 o 1") Optional<Integer> status) {

}
