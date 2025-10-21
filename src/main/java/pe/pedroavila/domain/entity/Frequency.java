package pe.pedroavila.domain.entity;

import pe.pedroavila.application.dto.UpdateFrequencyCommand;

public record Frequency(
                Long id,
                int code,
                String name,
                int duration) {

        public Frequency withUpdatedData(UpdateFrequencyCommand dto) {
                return new Frequency(
                                this.id, // ID se mantiene
                                this.code, // Código se mantiene (asumiendo que es inmutable)

                                // Si el nombre está presente en el DTO, úsalo; si no, usa el nombre actual.
                                dto.name().orElse(this.name),

                                // Si la duración está presente, úsala; si no, usa la duración actual.
                                // Usamos orElseGet porque 'duration' es 'int' (primitivo) y su valor por
                                // defecto es 'this.duration'.
                                dto.duration().orElse(this.duration));
        }
}
