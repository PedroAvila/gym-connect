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
import pe.pedroavila.application.dto.CreateCustomer;
import pe.pedroavila.application.dto.UpdateCustomerCommand;
import pe.pedroavila.application.port.in.CreateCustomerUseCase;
import pe.pedroavila.application.port.in.GetByIdCustomerUseCase;
import pe.pedroavila.application.port.in.GetCustomerUseCase;
import pe.pedroavila.application.port.in.UpdateCustomerUseCase;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetByIdCustomerUseCase getByIdCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase,
            GetByIdCustomerUseCase getByIdCustomerUseCase, GetCustomerUseCase getCustomerUseCase,
            UpdateCustomerUseCase updateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getByIdCustomerUseCase = getByIdCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.getCustomerUseCase.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(this.getByIdCustomerUseCase.single(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateCustomer dto) {
        return new ResponseEntity<>(this.createCustomerUseCase.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UpdateCustomerCommand dto) {
        return new ResponseEntity<>(this.updateCustomerUseCase.update(id, dto), HttpStatus.OK);
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
