package pe.pedroavila.adapter.common;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateMapperHelper {

    private final ZoneId ZONE = ZoneId.of("America/Lima");
    private final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd/MM/yyyy hh:mm a")
            .withZone(ZONE);

    /**
     * Convierte un Instant (típicamente UTC) a un String en formato local.
     */
    public String map(Instant instant) {
        if (instant == null) {
            return null;
        }
        return FORMATTER.format(instant);
    }

    // Opcional: Si necesitas mapear de String de vuelta a Instant
    public Instant map(String dateString) {
        if (dateString == null) {
            return null;
        }
        // Nota: La deserialización requiere más cuidado con el Locale/TimeZone.
        // Se recomienda usar el ObjectMapper de Jackson para la entrada
        // (deserialización) en la API.
        return null; // Implementación omitida por simplicidad, enfócate en la salida
                     // (serialización).
    }

}
