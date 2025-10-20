package pe.pedroavila.domain.entity;

public record Frequency(
        Long id,
        int code,
        String name,
        int duration) {
}
