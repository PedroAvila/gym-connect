package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.UpdateGymCommand;
import pe.pedroavila.application.dto.UpdateGymResponse;

public interface UpdateGymUseCase {

    UpdateGymResponse update(Long id, UpdateGymCommand dto);
}
