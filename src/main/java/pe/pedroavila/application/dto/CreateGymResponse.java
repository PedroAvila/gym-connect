package pe.pedroavila.application.dto;

public record CreateGymResponse(
                Long id,
                int code,
                String name,
                String address,
                String phone,
                String createdAt) {

}
