package pe.pedroavila.application.dto;

public record GetPackageResponse(
        Long id,
        int code,
        String name,
        String description) {
}
