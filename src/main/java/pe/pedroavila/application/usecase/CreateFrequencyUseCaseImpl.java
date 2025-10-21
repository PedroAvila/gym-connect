package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.CreateFrequency;
import pe.pedroavila.application.dto.CreateFrequencyResponse;
import pe.pedroavila.application.port.in.CreateFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepository;

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

        int code = this.frequencyRepository.generateCode();
        var frequency = this.mapper.toDomain(dto, code);
        var entity = this.mapper.toEntity(frequency);
        var newFrequency = this.frequencyRepository.save(entity);
        return this.mapper.toCreateDto(newFrequency);
    }
}
