package pe.pedroavila.application.dto;

public record CreateCustomer(
        String name,
        int gender,
        String phone,
        String email,
        int age,
        String observations) {
}
