package pe.pedroavila.application.port.in;

import java.util.List;

import pe.pedroavila.application.dto.GetFrequencyResponse;

public interface GetFrequencyUseCase {

    List<GetFrequencyResponse> getAll();

}
