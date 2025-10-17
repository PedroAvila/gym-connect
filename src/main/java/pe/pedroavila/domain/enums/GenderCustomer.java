package pe.pedroavila.domain.enums;

import pe.pedroavila.adapter.repository.converter.IntEnum;

public enum GenderCustomer implements IntEnum {
    MALE(1),
    FEMALE(2);

    private final int value;

    GenderCustomer(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
