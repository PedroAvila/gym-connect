package pe.pedroavila.application.dto;

import java.util.Optional;

public record UpdateFrequencyCommand(
                Optional<String> name,
                Optional<Integer> duration) {

}
