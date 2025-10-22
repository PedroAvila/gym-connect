package pe.pedroavila.domain.enums;

import pe.pedroavila.adapter.common.IntEnumConverter;

public class StatusCustomerConverter extends IntEnumConverter<StatusCustomer> {

    public StatusCustomerConverter() {
        super(StatusCustomer.class);
    }
}
