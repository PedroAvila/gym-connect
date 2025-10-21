package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.CreateCustomerResponse;
import pe.pedroavila.application.port.in.CreateCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepository;

@Service
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CreateCustomerUseCaseImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CreateCustomerResponse create(CreateCustomer dto) {

        boolean existsByName = this.customerRepository.existsByName(dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a customer with that name: " +
                    dto.name(),
                    HttpStatus.CONFLICT);
        }

        int code = this.customerRepository.generateCode();
        var customer = this.mapper.toDomain(dto, code);
        var entity = this.mapper.toEntity(customer);
        var newCustomer = this.customerRepository.save(entity);
        return this.mapper.toCreateDto(newCustomer);
    }
}
