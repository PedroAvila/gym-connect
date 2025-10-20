package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.GetByIdFrequencyResponse;

public interface GetByIdFrequencyUseCase {

    GetByIdFrequencyResponse single(Long id);

}
