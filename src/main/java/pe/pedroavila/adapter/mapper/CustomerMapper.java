package pe.pedroavila.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.CreateCustomerResponse;
import pe.pedroavila.domain.entity.Customer;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface CustomerMapper {

    default Customer toDomain(CreateCustomer dto, int code) {
        return new Customer(dto, code);
    }

    @Mapping(target = "gender", source = "gender.value")
    @Mapping(target = "status", source = "status.value")
    CreateCustomerResponse toCreateDto(Customer customer);
}
