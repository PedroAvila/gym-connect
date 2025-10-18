package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.GetCustomerResponse;
import pe.pedroavila.application.port.in.GetCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepositoryPort;

@Service
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final CustomerMapper mapper;

    public GetCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort, CustomerMapper mapper) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public List<GetCustomerResponse> getAll() {
        var customers = this.customerRepositoryPort.getAll();
        return this.mapper.toDtoList(customers);
    }

}
