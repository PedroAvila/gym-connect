package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.GetByIdPackageResponse;

public interface GetByIdPackageUseCase {

    GetByIdPackageResponse single(Long id);
}
