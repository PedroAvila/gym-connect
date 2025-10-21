package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.GetByIdGymResponse;
import pe.pedroavila.application.port.in.GetByIdGymUseCase;
import pe.pedroavila.application.port.out.GymRepository;

@Service
public class GetByIdGymUseCaseImpl implements GetByIdGymUseCase {

    private final GymRepository gymRepository;
    private final GymMapper mapper;

    public GetByIdGymUseCaseImpl(GymRepository gymRepository, GymMapper mapper) {
        this.gymRepository = gymRepository;
        this.mapper = mapper;
    }

    @Override
    public GetByIdGymResponse single(Long id) {

        var entity = this.gymRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Gym not found: " + id,
                        HttpStatus.NOT_FOUND));

        var gym = this.mapper.toDomain(entity);

        return mapper.toSingleDto(gym);
    }
}
