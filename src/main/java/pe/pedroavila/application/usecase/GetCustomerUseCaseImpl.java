package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.CustomerMapper;
import pe.pedroavila.application.dto.GetCustomerResponse;
import pe.pedroavila.application.port.in.GetCustomerUseCase;
import pe.pedroavila.application.port.out.CustomerRepository;

@Service
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public GetCustomerUseCaseImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetCustomerResponse> getAll() {
        var entities = this.customerRepository.findAll();
        var customers = this.mapper.toDomainList(entities);
        return this.mapper.toDtoList(customers);
    }

}
