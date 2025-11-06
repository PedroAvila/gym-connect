package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.PackageMapper;
import pe.pedroavila.application.dto.GetByIdPackageResponse;
import pe.pedroavila.application.port.in.GetByIdPackageUseCase;
import pe.pedroavila.application.port.out.PackageRepository;

@Service
public class GetByIdPackageUseCaseImpl implements GetByIdPackageUseCase {

    private final PackageRepository packageRepository;
    private final PackageMapper mapper;

    public GetByIdPackageUseCaseImpl(PackageRepository packageRepository, PackageMapper mapper) {
        this.packageRepository = packageRepository;
        this.mapper = mapper;
    }

    @Override
    public GetByIdPackageResponse single(Long id) {
        var pkg = this.packageRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Package not found: " + id, HttpStatus.NOT_FOUND));

        return this.mapper.toSingleDto(pkg);
    }
}
