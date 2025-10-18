package pe.pedroavila.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCustomerCommand(
        @NotBlank(message = "El nombre no puede estar vacío") @Size(max = 255, message = "El nombre no debe exceder los 255 caracteres") String name,

        @Min(value = 1, message = "El valor de género debe ser 1 (MALE) o 2 (FEMALE)") @Max(value = 2, message = "El valor de género debe ser 1 (MALE) o 2 (FEMALE)") int gender,

        @Size(max = 9, message = "El teléfono no debe exceder los 9 dígitos") @NotBlank(message = "La teléfono es requerido") @Pattern(regexp = "^$|^[0-9]{9}$", message = "El teléfono debe tener exactamente 9 dígitos numéricos o estar vacío") String phone,

        @NotBlank(message = "El email no puede estar vacío") @Email(message = "El formato del email es inválido") @Size(max = 255, message = "El email no debe exceder los 255 caracteres") String email,

        @Min(value = 1, message = "La edad debe ser mayor a 0") int age,
        String observations,

        @Min(value = 0, message = "El estado debe ser 0 o 1") @Max(value = 1, message = "El estado debe ser 0 o 1") int status) {

}
