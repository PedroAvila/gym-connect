package pe.pedroavila.application.dto;

public record UpdatePackageResponse(
        Long id,
        int code,
        String name,
        String description) {

}
