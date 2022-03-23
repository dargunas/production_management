package appProjectWorkingWithProduct;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void deleteByEmployeeId (Integer employeeId){
        Transaction transaction = session.beginTransaction();
        session.delete(findByEmployeeId(employeeId));
        transaction.commit();
        System.out.println("Employee was deleted " + findByEmployeeId(employeeId));
    }

    public void saveEmployee (Employee employee){
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        System.out.println("Employee was added " + employee);
    }

    public Employee findByEmployeeId (Integer employeeId){
        return session.find(Employee.class, employeeId);
    }

    public void addingProductToEmployee (Integer employeeId, Long productNumber){
        ProductRepository productRepository = new ProductRepository();
        Transaction transaction = session.beginTransaction();
        Employee employeeToConnect = findByEmployeeId(employeeId);
        Product productToBeAddedToEmployee = productRepository.findByProductNumber(productNumber);
        List<Product> productsToBeAddedToEmployee = new ArrayList<>();
        productsToBeAddedToEmployee.add(productToBeAddedToEmployee);
        employeeToConnect.setProducts(productsToBeAddedToEmployee);
        session.persist(employeeToConnect);
        transaction.commit();
        System.out.println("New product " + productToBeAddedToEmployee + " was added to employee " + employeeToConnect);
    }
}
