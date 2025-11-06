package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.CreatePackage;
import pe.pedroavila.application.dto.CreatePackageResponse;

public interface CreatePackageUseCase {
    CreatePackageResponse create(CreatePackage dto);
}
