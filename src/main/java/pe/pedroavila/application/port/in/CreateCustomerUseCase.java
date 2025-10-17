package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.CreateCustomerResponse;

public interface CreateCustomerUseCase {
    CreateCustomerResponse create(CreateCustomer dto);
}
