package pe.pedroavila.application.port.in;

import java.util.List;

import pe.pedroavila.application.dto.GetCustomerResponse;

public interface GetCustomerUseCase {

    List<GetCustomerResponse> getAll();

}
