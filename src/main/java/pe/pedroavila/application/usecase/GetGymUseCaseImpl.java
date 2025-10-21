package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.GetGymResponse;
import pe.pedroavila.application.port.in.GetGymUseCase;
import pe.pedroavila.application.port.out.GymRepository;

@Service
public class GetGymUseCaseImpl implements GetGymUseCase {

    private final GymRepository gymRepository;
    private final GymMapper mapper;

    public GetGymUseCaseImpl(GymRepository gymRepository, GymMapper mapper) {
        this.gymRepository = gymRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetGymResponse> getAll() {
        var entities = this.gymRepository.findAll();
        var gyms = this.mapper.toDomainList(entities);
        return this.mapper.toDtoList(gyms);
    }
}
