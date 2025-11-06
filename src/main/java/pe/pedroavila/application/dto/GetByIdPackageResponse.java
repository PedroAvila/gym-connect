package pe.pedroavila.application.dto;

public record GetByIdPackageResponse(
        Long id,
        int code,
        String name,
        String description) {

}
