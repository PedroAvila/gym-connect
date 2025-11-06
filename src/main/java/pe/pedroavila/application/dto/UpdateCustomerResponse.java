package pe.pedroavila.application.dto;

public record UpdateCustomerResponse(
                Long id,
                int code,
                String name,
                int gender,
                String phone,
                String email,
                int age,
                String createdAt,
                String observations,
                int status) {

}
