package pe.pedroavila.application.dto;

public record GetFrequencyResponse(
        Long id,
        int code,
        String name,
        int duration) {

}
