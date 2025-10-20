package pe.pedroavila.application.dto;

public record CreateFrequencyResponse(
        Long id,
        int code,
        String name,
        int duration) {

}
