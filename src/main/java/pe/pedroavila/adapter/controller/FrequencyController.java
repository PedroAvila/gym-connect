package pe.pedroavila.adapter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.pedroavila.application.dto.CreateFrequency;
import pe.pedroavila.application.dto.CreateFrequencyResponse;
import pe.pedroavila.application.dto.GetByIdFrequencyResponse;
import pe.pedroavila.application.dto.GetFrequencyResponse;
import pe.pedroavila.application.dto.UpdateFrequencyCommand;
import pe.pedroavila.application.dto.UpdateFrequencyResponse;
import pe.pedroavila.application.port.in.CreateFrequencyUseCase;
import pe.pedroavila.application.port.in.GetByIdFrequencyUseCase;
import pe.pedroavila.application.port.in.GetFrequencyUseCase;
import pe.pedroavila.application.port.in.UpdateFrequencyUseCase;

@RestController
@RequestMapping("/frequencys")
public class FrequencyController {

    private final CreateFrequencyUseCase createFrequencyUseCase;
    private final GetByIdFrequencyUseCase getByIdFrequencyUseCase;
    private final GetFrequencyUseCase getFrequencyUseCase;
    private final UpdateFrequencyUseCase updateFrequencyUseCase;

    public FrequencyController(CreateFrequencyUseCase createFrequencyUseCase,
            GetByIdFrequencyUseCase getByIdFrequencyUseCase, GetFrequencyUseCase getFrequencyUseCase,
            UpdateFrequencyUseCase updateFrequencyUseCase) {
        this.createFrequencyUseCase = createFrequencyUseCase;
        this.getByIdFrequencyUseCase = getByIdFrequencyUseCase;
        this.getFrequencyUseCase = getFrequencyUseCase;
        this.updateFrequencyUseCase = updateFrequencyUseCase;
    }

    @GetMapping()
    public ResponseEntity<List<GetFrequencyResponse>> findAll() {
        return new ResponseEntity<>(this.getFrequencyUseCase.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdFrequencyResponse> find(@PathVariable Long id) {
        return new ResponseEntity<>(this.getByIdFrequencyUseCase.single(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CreateFrequencyResponse> create(@RequestBody @Valid CreateFrequency dto) {
        return new ResponseEntity<>(this.createFrequencyUseCase.create(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateFrequencyResponse> update(@PathVariable Long id,
            @RequestBody @Valid UpdateFrequencyCommand dto) {
        return new ResponseEntity<>(this.updateFrequencyUseCase.update(id, dto), HttpStatus.OK);
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
