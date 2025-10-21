package pe.pedroavila.adapter.jpa;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
@DynamicUpdate(true)
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private String name;

    private Integer gender;

    @Column(length = 10)
    private String phone;

    private String email;

    private Integer age;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    private String observations;

    private Integer status;

}
