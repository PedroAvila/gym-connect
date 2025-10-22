package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.CreateGym;
import pe.pedroavila.application.dto.CreateGymResponse;
import pe.pedroavila.application.port.in.CreateGymUseCase;
import pe.pedroavila.application.port.out.GymRepository;
import pe.pedroavila.domain.entity.Gym;

@Service
public class CreateGymUseCaseImpl implements CreateGymUseCase {

    private final GymRepository gymRepository;
    private final GymMapper mapper;

    public CreateGymUseCaseImpl(GymRepository gymRepository, GymMapper mapper) {
        this.gymRepository = gymRepository;
        this.mapper = mapper;
    }

    @Override
    public CreateGymResponse create(CreateGym dto) {

        boolean existsByName = this.gymRepository.existsByName(dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a gym with that name: " + dto.name(), HttpStatus.CONFLICT);
        }

        var gym = new Gym();
        gym.setCode(this.gymRepository.generateCode());
        gym.setName(dto.name());
        gym.setAddress(dto.address());
        gym.setPhone(dto.phone());

        var newUser = this.gymRepository.save(gym);
        return mapper.toCreateDto(newUser);
    }
}
