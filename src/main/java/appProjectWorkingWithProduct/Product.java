package appProjectWorkingWithProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private List<Employee> employees;

}