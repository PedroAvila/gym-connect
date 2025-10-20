package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.FrequencyMapper;
import pe.pedroavila.application.dto.GetFrequencyResponse;
import pe.pedroavila.application.port.in.GetFrequencyUseCase;
import pe.pedroavila.application.port.out.FrequencyRepositoryPort;

@Service
public class GetFrequencyUseCaseImpl implements GetFrequencyUseCase {

    private final FrequencyRepositoryPort frequencyRepositoryPort;
    private final FrequencyMapper mapper;

    public GetFrequencyUseCaseImpl(FrequencyRepositoryPort frequencyRepositoryPort, FrequencyMapper mapper) {
        this.frequencyRepositoryPort = frequencyRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public List<GetFrequencyResponse> getAll() {
        var frequencys = this.frequencyRepositoryPort.getAll();
        return this.mapper.toDtoList(frequencys);
    }
}
