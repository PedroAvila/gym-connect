package pe.pedroavila.domain.entity;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.pedroavila.domain.enums.GenderCustomer;
import pe.pedroavila.domain.enums.GenderCustomerConverter;
import pe.pedroavila.domain.enums.StatusCustomer;
import pe.pedroavila.domain.enums.StatusCustomerConverter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
@DynamicUpdate(true)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private String name;

    @Convert(converter = GenderCustomerConverter.class)
    private GenderCustomer gender;

    @Column(length = 10)
    private String phone;

    private String email;

    private Integer age;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    private String observations;

    @Convert(converter = StatusCustomerConverter.class)
    private StatusCustomer status = StatusCustomer.ENABLED;
}
