package pe.pedroavila.application.dto;

public record CreatePackageResponse(
        Long id,
        int code,
        String name,
        String description) {
}
