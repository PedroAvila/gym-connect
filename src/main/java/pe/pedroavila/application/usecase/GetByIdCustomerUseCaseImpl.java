package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.GetByIdCustomerResponse;
import pe.pedroavila.application.port.in.GetByIdCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepositoryPort;

@Service
public class GetByIdCustomerUseCaseImpl implements GetByIdCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final CustomerMapper mapper;

    public GetByIdCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort, CustomerMapper mapper) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public GetByIdCustomerResponse single(Long id) {

        var customer = this.customerRepositoryPort.single(id)
                .orElseThrow(() -> new BusinessException("Customer not found: " + id, HttpStatus.NOT_FOUND));

        return this.mapper.toSingleDto(customer);
    }
}
