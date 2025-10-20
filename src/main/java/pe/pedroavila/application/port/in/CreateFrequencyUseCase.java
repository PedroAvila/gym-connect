package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.CreateFrequency;
import pe.pedroavila.application.dto.CreateFrequencyResponse;

public interface CreateFrequencyUseCase {

    CreateFrequencyResponse create(CreateFrequency dto);

}
