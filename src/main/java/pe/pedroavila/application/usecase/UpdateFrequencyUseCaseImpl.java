package pe.pedroavila.application.usecase;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.UpdateFrequencyCommand;
import pe.pedroavila.application.dto.UpdateFrequencyResponse;
import pe.pedroavila.application.port.in.UpdateFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepositoryPort;
import pe.pedroavila.domain.entity.Frequency;

@Service
public class UpdateFrequencyUseCaseImpl implements UpdateFrequencyUseCase {

    private final FrequencyRepositoryPort frequencyRepositoryPort;
    private final FrequencyMapper mapper;
    private final ObjectMapper objectMapper;

    public UpdateFrequencyUseCaseImpl(FrequencyRepositoryPort frequencyRepositoryPort, FrequencyMapper mapper,
            ObjectMapper objectMapper) {
        this.frequencyRepositoryPort = frequencyRepositoryPort;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public UpdateFrequencyResponse update(Long id, UpdateFrequencyCommand dto) {

        var frequency = this.frequencyRepositoryPort.single(id)
                .orElseThrow(() -> new BusinessException("Frequency not found: " + id, HttpStatus.NOT_FOUND));

        var frequencyUpdate = new Frequency(frequency.id(), frequency.code(), dto.name(), dto.duration());

        Map<String, Object> fields = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
        });

        this.frequencyRepositoryPort.update(Frequency.class, id, fields);
        return this.mapper.toUpdateDto(frequencyUpdate);
    }

}
