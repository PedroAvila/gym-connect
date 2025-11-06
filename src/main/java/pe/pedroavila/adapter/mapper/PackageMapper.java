package pe.pedroavila.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import pe.pedroavila.application.dto.CreatePackageResponse;
import pe.pedroavila.application.dto.GetByIdPackageResponse;
import pe.pedroavila.application.dto.GetPackageResponse;
import pe.pedroavila.application.dto.UpdatePackageResponse;
import pe.pedroavila.domain.entity.Package;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    CreatePackageResponse toCreateDto(pe.pedroavila.domain.entity.Package entity);

    GetByIdPackageResponse toSingleDto(Package pkg);

    List<GetPackageResponse> toDtoList(List<Package> packages);

    UpdatePackageResponse toUpdateDto(Package entity);
}
