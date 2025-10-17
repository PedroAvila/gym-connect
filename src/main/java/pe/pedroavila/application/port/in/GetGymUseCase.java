package pe.pedroavila.application.port.in;

import java.util.List;

import pe.pedroavila.application.dto.GetGymResponse;

public interface GetGymUseCase {

    List<GetGymResponse> getAll();
}
