package pe.pedroavila.domain.enums;

import jakarta.persistence.Converter;
import pe.pedroavila.adapter.common.IntEnumConverter;

@Converter
public class GenderCustomerConverter extends IntEnumConverter<GenderCustomer> {

    public GenderCustomerConverter() {
        super(GenderCustomer.class);
    }
}
