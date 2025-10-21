package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.adapter.jpa.GymEntity;
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

    @Mapping(target = "withUpdatedData", ignore = true)
    Gym toDomain(GymEntity entity);

    GymEntity toEntity(Gym gym);

    // CreateGymResponse toCreateDto(Gym gym);
    CreateGymResponse toCreateDto(GymEntity entity);

    // UpdateGymResponse toUpdateDto(Gym gym);

    UpdateGymResponse toUpdateDto(GymEntity entity);

    GetByIdGymResponse toSingleDto(Gym gym);

    List<Gym> toDomainList(List<GymEntity> entities);

    List<GetGymResponse> toDtoList(List<Gym> gyms);

}
