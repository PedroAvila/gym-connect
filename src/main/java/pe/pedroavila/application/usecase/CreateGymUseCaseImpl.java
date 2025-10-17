package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.jpa.GymEntity;
import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.CreateGym;
import pe.pedroavila.application.dto.CreateGymResponse;
import pe.pedroavila.application.port.in.CreateGymUseCase;
import pe.pedroavila.application.port.out.GymRepositoryPort;

@Service
public class CreateGymUseCaseImpl implements CreateGymUseCase {

    private final GymRepositoryPort gymRepositoryPort;
    private final GymMapper mapper;

    public CreateGymUseCaseImpl(GymRepositoryPort gymRepositoryPort, GymMapper mapper) {
        this.gymRepositoryPort = gymRepositoryPort;
        this.mapper = mapper;
    }

    @Override
    public CreateGymResponse create(CreateGym dto) {

        boolean existsByName = this.gymRepositoryPort.existsByName(GymEntity.class, dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a gym with that name: " + dto.name(), HttpStatus.CONFLICT);
        }

        int code = gymRepositoryPort.generateCode();
        var entity = mapper.toDomain(dto, code);
        var newUser = this.gymRepositoryPort.create(entity);
        return mapper.toCreateDto(newUser);

    }

}
