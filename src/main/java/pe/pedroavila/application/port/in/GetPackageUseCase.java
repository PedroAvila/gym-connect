package pe.pedroavila.application.port.in;

import java.util.List;

import pe.pedroavila.application.dto.GetPackageResponse;

public interface GetPackageUseCase {

    List<GetPackageResponse> getAll();

}
