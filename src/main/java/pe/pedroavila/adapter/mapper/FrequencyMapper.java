package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import pe.pedroavila.adapter.common.DateMapperHelper;
import pe.pedroavila.application.dto.CreateFrequencyResponse;
import pe.pedroavila.application.dto.GetByIdFrequencyResponse;
import pe.pedroavila.application.dto.GetFrequencyResponse;
import pe.pedroavila.application.dto.UpdateFrequencyResponse;
import pe.pedroavila.domain.entity.Frequency;

@Mapper(componentModel = "spring", uses = DateMapperHelper.class)
public interface FrequencyMapper {

    CreateFrequencyResponse toCreateDto(Frequency entity);

    GetByIdFrequencyResponse toSingleDto(Frequency frequency);

    List<GetFrequencyResponse> toDtoList(List<Frequency> frequencys);

    UpdateFrequencyResponse toUpdateDto(Frequency entity);

}
