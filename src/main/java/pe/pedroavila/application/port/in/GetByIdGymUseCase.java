package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.GetByIdGymResponse;

public interface GetByIdGymUseCase {

    GetByIdGymResponse single(Long id);

}
