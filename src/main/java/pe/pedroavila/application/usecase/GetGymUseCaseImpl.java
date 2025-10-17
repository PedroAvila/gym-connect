package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.GetGymResponse;
import pe.pedroavila.application.port.in.GetGymUseCase;
import pe.pedroavila.application.port.out.GymRepositoryPort;

@Service
public class GetGymUseCaseImpl implements GetGymUseCase {

    private final GymRepositoryPort gymRepositoryPort;
    private final GymMapper mapper;

    public GetGymUseCaseImpl(GymRepositoryPort gymRepositoryPort, GymMapper mapper) {
        this.gymRepositoryPort = gymRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public List<GetGymResponse> getAll() {
        var gyms = this.gymRepositoryPort.getAll();
        return this.mapper.toDtoList(gyms);
    }
}
