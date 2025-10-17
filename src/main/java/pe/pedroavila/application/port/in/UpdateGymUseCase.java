package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.UpdateGymResponse;
import pe.pedroavila.application.dto.UpdateGymWithId;

public interface UpdateGymUseCase {

    UpdateGymResponse update(UpdateGymWithId dto);
}
