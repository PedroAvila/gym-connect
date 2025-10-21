package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.adapter.jpa.FrequencyEntity;
import pe.pedroavila.application.dto.CreateFrequency;
import pe.pedroavila.application.dto.CreateFrequencyResponse;
import pe.pedroavila.application.dto.GetByIdFrequencyResponse;
import pe.pedroavila.application.dto.GetFrequencyResponse;
import pe.pedroavila.application.dto.UpdateFrequencyResponse;
import pe.pedroavila.domain.entity.Frequency;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface FrequencyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "withUpdatedData", ignore = true)
    Frequency toDomain(CreateFrequency dto, int code);

    @Mapping(target = "withUpdatedData", ignore = true)
    Frequency toDomain(FrequencyEntity entity);

    FrequencyEntity toEntity(Frequency frequency);

    // CreateFrequencyResponse toCreateDto(Frequency frequency);
    CreateFrequencyResponse toCreateDto(FrequencyEntity entity);

    GetByIdFrequencyResponse toSingleDto(Frequency frequency);

    // GetFrequencyResponse toDto(Frequency frequency);

    List<Frequency> toDomainList(List<FrequencyEntity> entities);

    List<GetFrequencyResponse> toDtoList(List<Frequency> frequencys);

    UpdateFrequencyResponse toUpdateDto(FrequencyEntity entity);

}
