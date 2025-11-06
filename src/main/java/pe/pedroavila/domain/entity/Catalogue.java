package pe.pedroavila.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.pedroavila.domain.enums.StatusCatalogue;
import pe.pedroavila.domain.enums.StatusCatalogueConverter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "catalogues")
@DynamicUpdate(true)
public class Catalogue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    @Column(name = "frequency_id", nullable = false)
    private Long frequencyId;

    @Column(name = "package_id", nullable = false)
    private Long packageId;

    private String name;

    @Column(name = "discount_rate", precision = 10, scale = 2)
    private BigDecimal discountRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Convert(converter = StatusCatalogueConverter.class)
    private StatusCatalogue status = StatusCatalogue.ENABLED;

    // Relación con Frequency
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequency_id", insertable = false, updatable = false)
    private Frequency frequency;

    // Relación con Package
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", insertable = false, updatable = false)
    private Package packageEntity;
}
