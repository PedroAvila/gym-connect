package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.UpdateFrequencyCommand;
import pe.pedroavila.application.dto.UpdateFrequencyResponse;

public interface UpdateFrequencyUseCase {

    UpdateFrequencyResponse update(Long id, UpdateFrequencyCommand dto);

}
