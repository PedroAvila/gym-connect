package pe.pedroavila.application.dto;

public record GetByIdGymResponse(Long id,
        int code,
        String name,
        String address,
        String phone,
        String createdAt) {

}
