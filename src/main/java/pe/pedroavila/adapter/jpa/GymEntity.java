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
@Table(name = "gyms")
@DynamicUpdate(true)
public class GymEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private String name;

    private String address;

    @Column(length = 10)
    private String phone;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}