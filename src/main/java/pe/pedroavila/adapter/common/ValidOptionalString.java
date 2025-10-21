package pe.pedroavila.adapter.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalStringValidator.class)
@Documented
public @interface ValidOptionalString {

    String message() default "El valor dentro del Optional no es válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // Restricciones de validación
    int min() default 0;

    int max() default 255;

    String regexp() default ".*";

    // Nuevo: permitir valores null en el Optional
    boolean allowNull() default false;
}
