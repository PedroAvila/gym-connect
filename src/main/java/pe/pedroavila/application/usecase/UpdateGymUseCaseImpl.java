package pe.pedroavila.application.usecase;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.GymMapper;
import pe.pedroavila.application.dto.UpdateGymCommand;
import pe.pedroavila.application.dto.UpdateGymResponse;
import pe.pedroavila.application.port.in.UpdateGymUseCase;
import pe.pedroavila.application.port.out.GymRepositoryPort;
import pe.pedroavila.domain.entity.Gym;

@Service
public class UpdateGymUseCaseImpl implements UpdateGymUseCase {

    private final GymRepositoryPort gymRepositoryPort;
    private final GymMapper mapper;

    private final ObjectMapper objectMapper;

    public UpdateGymUseCaseImpl(GymRepositoryPort gymRepositoryPort, GymMapper mapper, ObjectMapper objectMapper) {
        this.gymRepositoryPort = gymRepositoryPort;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public UpdateGymResponse update(Long id, UpdateGymCommand dto) {

        var gym = gymRepositoryPort.single(id)
                .orElseThrow(() -> new BusinessException("Gym not found: " + id, HttpStatus.NOT_FOUND));

        var gymUpdate = new Gym(gym.id(), gym.code(), dto.name(),
                dto.address(), dto.phone(),
                gym.createdAt());

        Map<String, Object> fields = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
        });

        this.gymRepositoryPort.update(Gym.class, id, fields);

        return mapper.toUpdateDto(gymUpdate);
    }

}
