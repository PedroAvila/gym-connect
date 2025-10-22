package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.common.EnumUtils;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.UpdateCustomerCommand;
import pe.pedroavila.application.dto.UpdateCustomerResponse;
import pe.pedroavila.application.port.in.UpdateCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepository;
import pe.pedroavila.domain.enums.GenderCustomer;
import pe.pedroavila.domain.enums.StatusCustomer;

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

        var customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found: " + id,
                        HttpStatus.NOT_FOUND));

        dto.name().ifPresent(customer::setName);
        dto.phone().ifPresent(customer::setPhone);
        dto.email().ifPresent(customer::setEmail);
        dto.age().ifPresent(customer::setAge);
        dto.gender().ifPresent(genderInt -> {
            GenderCustomer newGender = EnumUtils.fromInt(GenderCustomer.class, genderInt);
            customer.setGender(newGender);
        });
        dto.status().ifPresent(statusInt -> {
            StatusCustomer newStatus = EnumUtils.fromInt(StatusCustomer.class, statusInt);
            customer.setStatus(newStatus);
        });

        var savedEntity = this.customerRepository.save(customer);
        return mapper.toUpdateDto(savedEntity);
    }
}
