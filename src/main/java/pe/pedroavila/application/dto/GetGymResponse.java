package pe.pedroavila.application.dto;

public record GetGymResponse(
        Long id,
        int code,
        String name,
        String address,
        String phone,
        String createdAt) {

}
