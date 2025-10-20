package pe.pedroavila.application.dto;

public record GetByIdFrequencyResponse(
        Long id,
        int code,
        String name,
        int duration) {

}
