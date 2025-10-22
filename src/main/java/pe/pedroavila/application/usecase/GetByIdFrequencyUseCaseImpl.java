package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.GetByIdFrequencyResponse;
import pe.pedroavila.application.port.in.GetByIdFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepository;

@Service
public class GetByIdFrequencyUseCaseImpl implements GetByIdFrequencyUseCase {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyMapper mapper;

    public GetByIdFrequencyUseCaseImpl(FrequencyRepository frequencyRepository, FrequencyMapper mapper) {
        this.frequencyRepository = frequencyRepository;
        this.mapper = mapper;
    }

    @Override
    public GetByIdFrequencyResponse single(Long id) {
        var frequency = this.frequencyRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Frequency not found: " + id,
                        HttpStatus.NOT_FOUND));

        return this.mapper.toSingleDto(frequency);
    }
}
