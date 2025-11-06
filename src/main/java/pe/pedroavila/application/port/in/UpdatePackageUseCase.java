package pe.pedroavila.application.port.in;

import pe.pedroavila.application.dto.UpdatePackageCommand;
import pe.pedroavila.application.dto.UpdatePackageResponse;

public interface UpdatePackageUseCase {

    UpdatePackageResponse update(Long id, UpdatePackageCommand dto);
}
