package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.GetByIdFrequencyResponse;
import pe.pedroavila.application.port.in.GetByIdFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepositoryPort;

@Service
public class GetByIdFrequencyUseCaseImpl implements GetByIdFrequencyUseCase {

    private final FrequencyRepositoryPort frequencyRepositoryPort;
    private final FrequencyMapper mapper;

    public GetByIdFrequencyUseCaseImpl(FrequencyRepositoryPort frequencyRepositoryPort, FrequencyMapper mapper) {
        this.frequencyRepositoryPort = frequencyRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public GetByIdFrequencyResponse single(Long id) {
        var frequency = this.frequencyRepositoryPort.single(id)
                .orElseThrow(() -> new BusinessException("Frequency not found: " + id, HttpStatus.NOT_FOUND));

        return this.mapper.toSingleDto(frequency);
    }
}
