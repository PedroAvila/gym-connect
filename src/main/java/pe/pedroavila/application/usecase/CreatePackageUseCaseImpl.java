package pe.pedroavila.application.usecase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.common.BusinessException;
import pe.pedroavila.adapter.mapper.PackageMapper;
import pe.pedroavila.application.dto.CreatePackage;
import pe.pedroavila.application.dto.CreatePackageResponse;
import pe.pedroavila.application.port.in.CreatePackageUseCase;
import pe.pedroavila.application.port.out.PackageRepository;
import pe.pedroavila.domain.entity.Package;

@Service
public class CreatePackageUseCaseImpl implements CreatePackageUseCase {

    private final PackageRepository packageRepository;
    private final PackageMapper mapper;

    public CreatePackageUseCaseImpl(PackageRepository packageRepository, PackageMapper mapper) {
        this.packageRepository = packageRepository;
        this.mapper = mapper;
    }

    @Override
    public CreatePackageResponse create(CreatePackage dto) {

        boolean existsByName = this.packageRepository.existsByName(dto.name());
        if (existsByName) {
            throw new BusinessException("There is already a package with that name: " +
                    dto.name(),
                    HttpStatus.CONFLICT);
        }

        var pack = new Package();
        pack.setCode(this.packageRepository.generateCode());
        pack.setName(dto.name());
        pack.setDescription(dto.description());

        var newPackage = this.packageRepository.save(pack);
        return this.mapper.toCreateDto(newPackage);
    }
}
