package appProjectWorkingWithProduct;

import appProjectWorkingWithProduct.connectionUtils.HibernateUtil;
import appProjectWorkingWithProduct.repository.EmployeeRepository;
import appProjectWorkingWithProduct.repository.ProductRepository;
import appProjectWorkingWithProduct.sqlClasses.Employee;
import appProjectWorkingWithProduct.sqlClasses.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAndProductAssignment {

    Session session = HibernateUtil.getSessionFactory().openSession();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    ProductRepository productRepository = new ProductRepository();

    public void assignEmployeeWithProduct(Integer employeeId, Long productId) {
        Transaction transaction = session.beginTransaction();
        Employee employeeToAssign = employeeRepository.findByEmployeeId(employeeId);
        Product productToAssign = productRepository.findByProductId(productId);
        List<Product> products = new ArrayList<>();
        products.addAll(employeeToAssign.getProducts());
        products.add(productToAssign);
        for (Product product : products) {
            employeeToAssign.setProducts(products);
        }
        session.update(employeeToAssign);
        transaction.commit();
        System.out.println("Product was assigned " + employeeToAssign + employeeToAssign.getProducts());
    }
}
