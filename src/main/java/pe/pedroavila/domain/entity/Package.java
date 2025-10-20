package pe.pedroavila.domain.entity;

public record Package(
        Long id,
        int code,
        String name,
        String description) {

}
