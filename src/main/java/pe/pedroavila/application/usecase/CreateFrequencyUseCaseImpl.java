package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.CreateFrequency;
import pe.pedroavila.application.dto.CreateFrequencyResponse;
import pe.pedroavila.application.port.in.CreateFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepository;
import pe.pedroavila.domain.entity.Frequency;

@Service
public class CreateFrequencyUseCaseImpl implements CreateFrequencyUseCase {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyMapper mapper;

    public CreateFrequencyUseCaseImpl(FrequencyRepository frequencyRepository, FrequencyMapper mapper) {
        this.frequencyRepository = frequencyRepository;
        this.mapper = mapper;
    }

    @Override
    public CreateFrequencyResponse create(CreateFrequency dto) {

        boolean existsByName = this.frequencyRepository.existsByName(dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a frequency with that name: " +
                    dto.name(),
                    HttpStatus.CONFLICT);
        }

        var frequency = new Frequency();
        frequency.setCode(this.frequencyRepository.generateCode());
        frequency.setName(dto.name());
        frequency.setDuration(dto.duration());

        var newFrequency = this.frequencyRepository.save(frequency);
        return this.mapper.toCreateDto(newFrequency);
    }
}
