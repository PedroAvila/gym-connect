package pe.pedroavila.application.dto;

public record UpdateGymResponse(Long id,
        int code,
        String name,
        String address,
        String phone,
        String createdAt) {

}
