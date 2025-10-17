package pe.pedroavila.domain.enums;

import pe.pedroavila.adapter.repository.converter.IntEnum;

public enum StatusCustomer implements IntEnum {

    ENABLED(1),
    DISABLED(0);

    private final int value;

    StatusCustomer(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
