package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.GetByIdCustomerResponse;

public interface GetByIdCustomerUseCase {

    GetByIdCustomerResponse single(Long id);
}
