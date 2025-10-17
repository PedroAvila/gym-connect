package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.CreateGym;
import pe.pedroavila.application.dto.CreateGymResponse;

public interface CreateGymUseCase {

    CreateGymResponse create(CreateGym dto);
}
