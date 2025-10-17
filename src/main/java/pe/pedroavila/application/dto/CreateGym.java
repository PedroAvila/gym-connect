package pe.pedroavila.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateGym(
        @NotBlank(message = "El nombre es requerido") @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres") 
        String name,

        @NotBlank(message = "La dirección es requerida") @Size(min = 3, max = 200, message = "La dirección debe tener entre 3 y 200 caracteres") 
        String address,

        @NotBlank(message = "La teléfono es requerido") @Pattern(regexp = "^$|^[0-9]{9}$", message = "El teléfono debe tener exactamente 9 dígitos numéricos o estar vacío")
        String phone
        ) {
}
