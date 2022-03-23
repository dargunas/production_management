package appProjectWorkingWithProduct;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.*;


@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder

public class Product {

    @Id
    private Long productNumber;

    private Integer givenHoursForProduction;
    private Integer hoursLeftForProduction;
    private Integer hoursUsedForProduction;
    private Double priceOfProduct;
    private Date productionEndDate;
    private Date productionStartDate;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productNumber != null && Objects.equals(productNumber, product.productNumber);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}