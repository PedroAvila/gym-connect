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

        var gym = this.gymRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Gym not found: " + id, HttpStatus.NOT_FOUND));

        dto.name().ifPresent(gym::setName);
        dto.address().ifPresent(gym::setAddress);
        dto.phone().ifPresent(gym::setPhone);

        var savedEntity = this.gymRepository.save(gym);
        return this.mapper.toUpdateDto(savedEntity);
    }
}
