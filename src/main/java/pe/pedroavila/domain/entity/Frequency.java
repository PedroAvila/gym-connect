package pe.pedroavila.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "frequencys")
@DynamicUpdate(true)
public class Frequency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private int code;

    private String name;

    private int duration;

    // Relación inversa: Uno (Frequency) a Muchos (Catalogue)
    @OneToMany(mappedBy = "frequency", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Catalogue> catalogues = new HashSet<>(); // Usamos Set para evitar duplicados
}