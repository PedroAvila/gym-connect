package pe.pedroavila.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.pedroavila.adapter.mapper.PackageMapper;
import pe.pedroavila.application.dto.GetPackageResponse;
import pe.pedroavila.application.port.in.GetPackageUseCase;
import pe.pedroavila.application.port.out.PackageRepository;

@Service
public class GetPackageUseCaseImpl implements GetPackageUseCase {

    private final PackageRepository packageRepository;
    private final PackageMapper mapper;

    public GetPackageUseCaseImpl(PackageRepository packageRepository, PackageMapper mapper) {
        this.packageRepository = packageRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetPackageResponse> getAll() {
        var packages = this.packageRepository.findAll();
        return this.mapper.toDtoList(packages);
    }

}
