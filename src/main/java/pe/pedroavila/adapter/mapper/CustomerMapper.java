package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.application.dto.CreateCustomerResponse;
import pe.pedroavila.application.dto.GetByIdCustomerResponse;
import pe.pedroavila.application.dto.GetCustomerResponse;
import pe.pedroavila.application.dto.UpdateCustomerResponse;
import pe.pedroavila.domain.entity.Customer;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface CustomerMapper {

    CreateCustomerResponse toCreateDto(Customer entity);

    GetByIdCustomerResponse toSingleDto(Customer customer);

    GetCustomerResponse toDto(Customer customer);

    List<GetCustomerResponse> toDtoList(List<Customer> customers);

    UpdateCustomerResponse toUpdateDto(Customer entity);
}
