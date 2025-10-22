package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.UpdateFrequencyCommand;
import pe.pedroavila.application.dto.UpdateFrequencyResponse;
import pe.pedroavila.application.port.in.UpdateFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepository;

@Service
public class UpdateFrequencyUseCaseImpl implements UpdateFrequencyUseCase {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyMapper mapper;

    public UpdateFrequencyUseCaseImpl(FrequencyRepository frequencyRepository, FrequencyMapper mapper) {
        this.frequencyRepository = frequencyRepository;
        this.mapper = mapper;
    }

    @Override
    public UpdateFrequencyResponse update(Long id, UpdateFrequencyCommand dto) {

        var frequency = this.frequencyRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Frequency not found: " + id, HttpStatus.NOT_FOUND));

        dto.name().ifPresent(frequency::setName);
        dto.duration().ifPresent(frequency::setDuration);

        var savedEntity = this.frequencyRepository.save(frequency);
        return this.mapper.toUpdateDto(savedEntity);
    }

}
