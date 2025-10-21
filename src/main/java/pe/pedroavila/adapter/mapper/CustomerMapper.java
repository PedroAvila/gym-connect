package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.adapter.jpa.CustomerEntity;
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.CreateCustomerResponse;
import pe.pedroavila.application.dto.GetByIdCustomerResponse;
import pe.pedroavila.application.dto.GetCustomerResponse;
import pe.pedroavila.application.dto.UpdateCustomerResponse;
import pe.pedroavila.domain.entity.Customer;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface CustomerMapper {

    default Customer toDomain(CreateCustomer dto, int code) {
        return new Customer(dto, code);
    }

    @Mapping(target = "gender", source = "gender.value")
    @Mapping(target = "status", source = "status.value")
    CustomerEntity toEntity(Customer customer);

    @Mapping(target = "withUpdatedData", ignore = true)
    Customer toDomain(CustomerEntity entity);

    CreateCustomerResponse toCreateDto(CustomerEntity entity);

    @Mapping(target = "gender", source = "gender.value")
    @Mapping(target = "status", source = "status.value")
    GetByIdCustomerResponse toSingleDto(Customer customer);

    List<Customer> toDomainList(List<CustomerEntity> entities);

    @Mapping(target = "gender", source = "gender.value")
    @Mapping(target = "status", source = "status.value")
    GetCustomerResponse toDto(Customer customer);

    List<GetCustomerResponse> toDtoList(List<Customer> customers);

    UpdateCustomerResponse toUpdateDto(CustomerEntity entity);
}
