package pe.pedroavila.application.usecase;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.common.EnumUtils;
import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.UpdateCustomerCommand;
import pe.pedroavila.application.dto.UpdateCustomerResponse;
import pe.pedroavila.application.port.in.UpdateCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepositoryPort;
import pe.pedroavila.domain.entity.Customer;
import pe.pedroavila.domain.enums.GenderCustomer;
import pe.pedroavila.domain.enums.StatusCustomer;

@Service
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final CustomerMapper mapper;
    private final ObjectMapper objectMapper;

    public UpdateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort, CustomerMapper mapper,
            ObjectMapper objectMapper) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public UpdateCustomerResponse update(Long id, UpdateCustomerCommand dto) {

        var customer = this.customerRepositoryPort.single(id)
                .orElseThrow(() -> new BusinessException("Customer not found: " + id, HttpStatus.NOT_FOUND));

        var customerUpdate = new Customer(
                customer.id(),
                customer.code(),
                dto.name(),
                EnumUtils.fromInt(GenderCustomer.class, dto.gender()),
                dto.phone(),
                dto.email(),
                dto.age(),
                customer.createdAt(),
                dto.observations(),
                EnumUtils.fromInt(StatusCustomer.class, dto.status()));

        Map<String, Object> fields = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
        });

        this.customerRepositoryPort.update(Customer.class, id, fields);

        return mapper.toUpdateDto(customerUpdate);

    }
}
