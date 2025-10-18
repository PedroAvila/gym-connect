package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.UpdateCustomerCommand;
import pe.pedroavila.application.dto.UpdateCustomerResponse;

public interface UpdateCustomerUseCase {

    UpdateCustomerResponse update(Long id, UpdateCustomerCommand dto);

}
