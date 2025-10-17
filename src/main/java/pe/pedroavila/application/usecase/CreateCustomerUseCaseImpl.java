package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.jpa.CustomerEntity;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.CreateCustomerResponse;
import pe.pedroavila.application.port.in.CreateCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepositoryPort;

@Service
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final CustomerMapper mapper;

    public CreateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort, CustomerMapper mapper) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public CreateCustomerResponse create(CreateCustomer dto) {
        
        boolean existsByName = this.customerRepositoryPort.existsByName(CustomerEntity.class, dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a customer with that name: " + dto.name(), HttpStatus.CONFLICT);
        }

        int code = customerRepositoryPort.generateCode();
        var entity = this.mapper.toDomain(dto, code);
        var newCustomer = this.customerRepositoryPort.create(entity);
        return this.mapper.toCreateDto(newCustomer);
    }

}
