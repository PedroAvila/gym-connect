package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.jpa.FrequencyEntity;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.CreateFrequency;
import pe.pedroavila.application.dto.CreateFrequencyResponse;
import pe.pedroavila.application.port.in.CreateFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepositoryPort;

@Service
public class CreateFrequencyUseCaseImpl implements CreateFrequencyUseCase {

    private final FrequencyRepositoryPort frequencyRepositoryPort;
    private final FrequencyMapper mapper;

    public CreateFrequencyUseCaseImpl(FrequencyRepositoryPort frequencyRepositoryPort, FrequencyMapper mapper) {
        this.frequencyRepositoryPort = frequencyRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public CreateFrequencyResponse create(CreateFrequency dto) {

        boolean existsByName = this.frequencyRepositoryPort.existsByName(FrequencyEntity.class, dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a frequency with that name: " + dto.name(),
                    HttpStatus.CONFLICT);
        }

        int code = this.frequencyRepositoryPort.generateCode();
        var entity = this.mapper.toDomain(dto, code);
        var newFrequency = this.frequencyRepositoryPort.create(entity);
        return this.mapper.toCreateDto(newFrequency);
    }
}
