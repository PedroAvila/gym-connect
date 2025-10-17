package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.application.dto.CreateGym;
import pe.pedroavila.application.dto.CreateGymResponse;
import pe.pedroavila.application.dto.GetByIdGymResponse;
import pe.pedroavila.application.dto.GetGymResponse;
import pe.pedroavila.application.dto.UpdateGymResponse;
import pe.pedroavila.domain.entity.Gym;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface GymMapper {

    default Gym toDomain(CreateGym dto, int code) {
        return new Gym(dto, code);
    }

    CreateGymResponse toCreateDto(Gym gym);

    UpdateGymResponse toUpdateDto(Gym gym);

    GetByIdGymResponse toSingleDto(Gym gym);

    List<GetGymResponse> toDtoList(List<Gym> gyms);

}
