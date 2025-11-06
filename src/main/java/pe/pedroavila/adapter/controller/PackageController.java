package pe.pedroavila.adapter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.pedroavila.application.dto.CreatePackage;
import pe.pedroavila.application.dto.CreatePackageResponse;
import pe.pedroavila.application.dto.GetByIdPackageResponse;
import pe.pedroavila.application.dto.GetPackageResponse;
import pe.pedroavila.application.dto.UpdatePackageCommand;
import pe.pedroavila.application.port.in.CreatePackageUseCase;
import pe.pedroavila.application.port.in.GetByIdPackageUseCase;
import pe.pedroavila.application.port.in.GetPackageUseCase;
import pe.pedroavila.application.port.in.UpdatePackageUseCase;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private final CreatePackageUseCase createPackageUseCase;
    private final GetPackageUseCase getPackageUseCase;
    private final GetByIdPackageUseCase getByIdPackageUseCase;
    private final UpdatePackageUseCase updatePackageUseCase;

    public PackageController(CreatePackageUseCase createPackageUseCase, GetPackageUseCase getPackageUseCase,
            GetByIdPackageUseCase getByIdPackageUseCase, UpdatePackageUseCase updatePackageUseCase) {
        this.createPackageUseCase = createPackageUseCase;
        this.getPackageUseCase = getPackageUseCase;
        this.getByIdPackageUseCase = getByIdPackageUseCase;
        this.updatePackageUseCase = updatePackageUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<GetPackageResponse>> getAll() {
        return new ResponseEntity<>(this.getPackageUseCase.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdPackageResponse> find(@PathVariable Long id) {
        return new ResponseEntity<>(this.getByIdPackageUseCase.single(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreatePackageResponse> create(@Valid @RequestBody CreatePackage dto) {
        return new ResponseEntity<>(this.createPackageUseCase.create(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdatePackageCommand dto) {
        return new ResponseEntity<>(this.updatePackageUseCase.update(id, dto), HttpStatus.OK);
    }
}
