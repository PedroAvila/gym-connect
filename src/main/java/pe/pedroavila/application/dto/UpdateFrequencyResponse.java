package pe.pedroavila.application.dto;

public record UpdateFrequencyResponse(
        Long id,
        int code,
        String name,
        int duration) {

}
