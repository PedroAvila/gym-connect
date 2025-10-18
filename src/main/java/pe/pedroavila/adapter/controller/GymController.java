package pe.pedroavila.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.pedroavila.application.dto.CreateGym;
import pe.pedroavila.application.dto.UpdateGymCommand;
import pe.pedroavila.application.port.in.CreateGymUseCase;
import pe.pedroavila.application.port.in.GetByIdGymUseCase;
import pe.pedroavila.application.port.in.GetGymUseCase;
import pe.pedroavila.application.port.in.UpdateGymUseCase;

@RestController
@RequestMapping("/gyms")
public class GymController {

    private final CreateGymUseCase createGymUseCase;
    private final GetGymUseCase getGymUseCase;
    private final GetByIdGymUseCase getByIdGymUseCase;
    private final UpdateGymUseCase updateGymUseCase;

    public GymController(CreateGymUseCase createGymUseCase, GetGymUseCase getGymUseCase,
            GetByIdGymUseCase getByIdGymUseCase, UpdateGymUseCase updateGymUseCase) {
        this.createGymUseCase = createGymUseCase;
        this.getGymUseCase = getGymUseCase;
        this.getByIdGymUseCase = getByIdGymUseCase;
        this.updateGymUseCase = updateGymUseCase;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.getGymUseCase.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(this.getByIdGymUseCase.single(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateGym dto) {
        return new ResponseEntity<>(this.createGymUseCase.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateGymCommand dto) {
        return new ResponseEntity<>(updateGymUseCase.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            // TODO Implement Your Logic To Destroy Data And Return Result Through
            // ResponseEntity
            return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
