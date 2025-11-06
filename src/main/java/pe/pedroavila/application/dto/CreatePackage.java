package pe.pedroavila.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePackage(
        @NotBlank(message = "The name cannot be empty") 
        @Size(max = 255, message = "The name must not exceed 255 characters") 
        String name,
                
        @NotBlank(message = "The description cannot be empty")
        @Size(max = 500, message = "The description must not exceed 500 characters") 
        String description) {
}
