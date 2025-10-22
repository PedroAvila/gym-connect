package pe.pedroavila.adapter.common;

import java.util.Objects;

import jakarta.persistence.AttributeConverter;

public abstract class IntEnumConverter<E extends Enum<E> & IntEnum> implements AttributeConverter<E, Integer> {

    // Necesitamos la clase del Enum específico (GenderCustomer, StatusEnum, etc.)
    private final Class<E> enumClass;

    public IntEnumConverter(Class<E> enumClass) {
        this.enumClass = Objects.requireNonNull(enumClass, "Enum class must not be null");
    }

    // Convierte el Enum de Java (ej: GenderCustomer.MALE) al valor INT de la DB
    // (ej: 1)
    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        // Usamos el método getValue() de la interfaz IntEnum
        return attribute.getValue();
    }

    // Convierte el valor INT de la DB (ej: 1) al Enum de Java (ej:
    // GenderCustomer.MALE)
    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        // Usamos tu clase EnumUtils para la conversión
        return EnumUtils.fromInt(enumClass, dbData);
    }
}
