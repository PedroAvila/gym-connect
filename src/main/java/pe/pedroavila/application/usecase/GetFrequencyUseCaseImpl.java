package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.GetFrequencyResponse;
import pe.pedroavila.application.port.in.GetFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepository;

@Service
public class GetFrequencyUseCaseImpl implements GetFrequencyUseCase {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyMapper mapper;

    public GetFrequencyUseCaseImpl(FrequencyRepository frequencyRepository, FrequencyMapper mapper) {
        this.frequencyRepository = frequencyRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetFrequencyResponse> getAll() {
        var entities = this.frequencyRepository.findAll();
        var frequencys = this.mapper.toDomainList(entities);
        return this.mapper.toDtoList(frequencys);
    }
}
