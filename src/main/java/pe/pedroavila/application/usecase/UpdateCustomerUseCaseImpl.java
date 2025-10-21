package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.UpdateCustomerCommand;
import pe.pedroavila.application.dto.UpdateCustomerResponse;
import pe.pedroavila.application.port.in.UpdateCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepository;

@Service
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public UpdateCustomerUseCaseImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public UpdateCustomerResponse update(Long id, UpdateCustomerCommand dto) {

        var entity = this.customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found: " + id,
                        HttpStatus.NOT_FOUND));

        var customer = this.mapper.toDomain(entity);
        var updateDomanin = customer.withUpdatedData(dto);
        var updateEntity = this.mapper.toEntity(updateDomanin);

        var savedEntity = this.customerRepository.save(updateEntity);
        return mapper.toUpdateDto(savedEntity);
    }
}
