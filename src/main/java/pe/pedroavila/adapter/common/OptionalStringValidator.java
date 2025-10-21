package pe.pedroavila.adapter.common;

import java.util.Optional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalStringValidator implements ConstraintValidator<ValidOptionalString, Optional<String>> {

    private int min;
    private int max;
    private String regexp;
    private boolean allowNull;

    @Override
    public void initialize(ValidOptionalString constraint) {
        this.min = constraint.min();
        this.max = constraint.max();
        this.regexp = constraint.regexp();
        this.allowNull = constraint.allowNull();
    }

    @Override
    public boolean isValid(Optional<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return allowNull;
        }

        if (!value.isPresent()) {
            return true; // Optional vacío es válido
        }

        String actualValue = value.get();
        if (actualValue == null) {
            return allowNull;
        }

        // Validar longitud
        if (actualValue.length() < min || actualValue.length() > max) {
            return false;
        }

        // Validar patrón regex
        if (!actualValue.matches(regexp)) {
            return false;
        }

        return true;
    }

}
