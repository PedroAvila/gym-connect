package pe.pedroavila.adapter.common;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    // Constructor que obliga a especificar el HttpStatus
    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String format, HttpStatus status, Object... args) {
        super(String.format(format, args));
        this.message = String.format(format, args);
        this.status = status;
    }

    // Getters
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
