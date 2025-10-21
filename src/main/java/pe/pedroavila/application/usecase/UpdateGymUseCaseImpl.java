package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.UpdateGymCommand;
import pe.pedroavila.application.dto.UpdateGymResponse;
import pe.pedroavila.application.port.in.UpdateGymUseCase;
import pe.pedroavila.application.port.out.GymRepository;

@Service
public class UpdateGymUseCaseImpl implements UpdateGymUseCase {

    private final GymRepository gymRepository;
    private final GymMapper mapper;

    public UpdateGymUseCaseImpl(GymRepository gymRepository, GymMapper mapper) {
        this.gymRepository = gymRepository;
        this.mapper = mapper;
    }

    @Override
    public UpdateGymResponse update(Long id, UpdateGymCommand dto) {

        var entity = this.gymRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Gym not found: " + id, HttpStatus.NOT_FOUND));

        var gym = this.mapper.toDomain(entity);
        var updateDomain = gym.withUpdatedData(dto);
        var updateEntity = this.mapper.toEntity(updateDomain);

        var savedEntity = this.gymRepository.save(updateEntity);
        return this.mapper.toUpdateDto(savedEntity);
    }

}
