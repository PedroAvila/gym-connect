package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.application.dto.CreateGymResponse;
import pe.pedroavila.application.dto.GetByIdGymResponse;
import pe.pedroavila.application.dto.GetGymResponse;
import pe.pedroavila.application.dto.UpdateGymResponse;
import pe.pedroavila.domain.entity.Gym;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface GymMapper {

    // Gym toEntity(Gym gym);

    CreateGymResponse toCreateDto(Gym entity);

    UpdateGymResponse toUpdateDto(Gym entity);

    GetByIdGymResponse toSingleDto(Gym gym);

    List<GetGymResponse> toDtoList(List<Gym> gyms);

}
