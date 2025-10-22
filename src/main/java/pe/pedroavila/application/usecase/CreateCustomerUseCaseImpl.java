package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.common.EnumUtils;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.CreateCustomerResponse;
import pe.pedroavila.application.port.in.CreateCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepository;
import pe.pedroavila.domain.entity.Customer;
import pe.pedroavila.domain.enums.GenderCustomer;

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

        var customer = new Customer();
        customer.setCode(this.customerRepository.generateCode());
        customer.setName(dto.name());
        customer.setGender(EnumUtils.fromInt(GenderCustomer.class, dto.gender()));
        customer.setPhone(dto.phone());
        customer.setEmail(dto.email());
        customer.setAge(dto.age());
        customer.setObservations(dto.observations());

        var newCustomer = this.customerRepository.save(customer);
        return this.mapper.toCreateDto(newCustomer);
    }
}
