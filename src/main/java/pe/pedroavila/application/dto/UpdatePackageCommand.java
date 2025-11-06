package pe.pedroavila.application.dto;

import java.util.Optional;

import pe.pedroavila.adapter.common.ValidOptionalString;

public record UpdatePackageCommand(
        @ValidOptionalString(min = 2, max = 100, message = "The name must be between 2 and 100 characters.") 
        Optional<String> name,
                
        @ValidOptionalString(min = 2, max = 255, message = "The description must be between 2 and 255 characters.") 
        Optional<String> description) {
}
