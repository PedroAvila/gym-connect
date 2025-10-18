package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.GetByIdGymResponse;
import pe.pedroavila.application.port.in.GetByIdGymUseCase;
import pe.pedroavila.application.port.out.GymRepositoryPort;

@Service
public class GetByIdGymUseCaseImpl implements GetByIdGymUseCase {

    private final GymRepositoryPort gymRepositoryPort;
    private final GymMapper mapper;

    public GetByIdGymUseCaseImpl(GymRepositoryPort gymRepositoryPort, GymMapper mapper) {
        this.gymRepositoryPort = gymRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public GetByIdGymResponse single(Long id) {

        var gym = gymRepositoryPort.single(id)
                .orElseThrow(() -> new BusinessException("Gym not found: " + id, HttpStatus.NOT_FOUND));

        return mapper.toSingleDto(gym);
    }
}
