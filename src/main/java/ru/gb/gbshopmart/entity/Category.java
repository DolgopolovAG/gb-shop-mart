package ru.gb.gbshopmart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.gb.gbshopmart.entity.common.InfoEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
@EntityListeners(AuditingEntityListener.class)
public class Category extends InfoEntity {

    @Column(name = "tittle")
    private String tittle;

    @ManyToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    private Set<Product> products;

    public boolean addProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        return products.add(product);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", tittle='" + tittle + '\'' +
                '}';
    }

    @Builder
    public Category(Long id, int version, String createdBy, LocalDateTime createdDate, String lastModifiedBy,
                        LocalDateTime lastModifiedDate, String tittle) {
        super(id, version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.tittle = tittle;
    }
}
