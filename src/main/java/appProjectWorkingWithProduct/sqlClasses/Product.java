package appProjectWorkingWithProduct.sqlClasses;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Integer givenHoursForProduction;
    private Integer hoursLeftForProduction;
    private Integer hoursUsedForProduction;
    private Double priceOfProduct;
    private String productionEndDate;
    private String productionStartDate;
    private Integer isReady;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productId != null && Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}