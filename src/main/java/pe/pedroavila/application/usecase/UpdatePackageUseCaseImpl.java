package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.PackageMapper;
import pe.pedroavila.application.dto.UpdatePackageCommand;
import pe.pedroavila.application.dto.UpdatePackageResponse;
import pe.pedroavila.application.port.in.UpdatePackageUseCase;
import pe.pedroavila.application.port.out.PackageRepository;

@Service
public class UpdatePackageUseCaseImpl implements UpdatePackageUseCase {

    private final PackageRepository packageRepository;
    private final PackageMapper mapper;

    public UpdatePackageUseCaseImpl(PackageRepository packageRepository, PackageMapper mapper) {
        this.packageRepository = packageRepository;
        this.mapper = mapper;
    }

    @Override
    public UpdatePackageResponse update(Long id, UpdatePackageCommand dto) {
        var pkg = this.packageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Package not found: " + id, HttpStatus.NOT_FOUND));

        dto.name().ifPresent(pkg::setName);
        dto.description().ifPresent(pkg::setDescription);

        var saveEntity = this.packageRepository.save(pkg);
        return mapper.toUpdateDto(saveEntity);
    }
}
