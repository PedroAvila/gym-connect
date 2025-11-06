package pe.pedroavila.adapter.common;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        logger.error("Business Error: {}, Time: {}", ex.getMessage(), LocalDateTime.now());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "Error de negocio");
        body.put("detail", ex.getMessage());
        body.put("status", ex.getStatus().value());
        body.put("instance", request.getDescription(false).replace("uri=", ""));
        body.put("timestamp", LocalDateTime.now());
        body.put("traceId", getTraceId(request));

        return new ResponseEntity<>(body, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        logger.error("Unexpected Error: {}, Time: {}", ex.getMessage(), LocalDateTime.now());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "Error interno del servidor");
        body.put("detail", "Ocurrió un error inesperado");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("instance", request.getDescription(false).replace("uri=", ""));
        body.put("timestamp", LocalDateTime.now());
        body.put("traceId", getTraceId(request));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        logger.error("Validation Error: {}, Time: {}", ex.getMessage(), LocalDateTime.now());

        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "Error de validación");
        body.put("detail", "Uno o más campos no son válidos");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("instance", request.getDescription(false).replace("uri=", ""));
        body.put("timestamp", LocalDateTime.now());
        body.put("traceId", getTraceId(request));
        body.put("validationErrors", validationErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private String getTraceId(WebRequest request) {
        return UUID.randomUUID().toString();
    }
}
