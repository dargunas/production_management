package appProjectWorkingWithProduct;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void deleteByProductNumber(Long productNumber) {
        Transaction transaction = session.beginTransaction();
        session.delete(findByProductNumber(productNumber));
        transaction.commit();
        System.out.println("Product was deleted " + findByProductNumber(productNumber));
    }

    public void saveProduct(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        System.out.println("Product was added " + product);
    }

    public Product findByProductNumber(Long productNumber) {
        return session.find(Product.class, productNumber);
    }

    public void modifyUsedHoursForProduction(Long productNumber, Integer hoursUsedForProductionToAdd) {
        Transaction transaction = session.beginTransaction();
        Product productToModify = findByProductNumber(productNumber);
        Integer spentHours = productToModify.getHoursUsedForProduction() + hoursUsedForProductionToAdd;
        Integer leftHours = productToModify.getHoursLeftForProduction() - hoursUsedForProductionToAdd;
        productToModify.setHoursUsedForProduction(spentHours);
        productToModify.setHoursLeftForProduction(leftHours);
        session.persist(productToModify);
        transaction.commit();
        System.out.println("hours was added");
    }

    public void addingEmployeeToProduct(Long productNumber, Integer employeeId){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Transaction transaction = session.beginTransaction();
        Product productToAdd = findByProductNumber(productNumber);
        Employee employeeToGetProduct = employeeRepository.findByEmployeeId(employeeId);
        List<Employee> employeesThatHasProduct = new ArrayList<>();
        employeesThatHasProduct.add(employeeToGetProduct);
        productToAdd.setEmployees(employeesThatHasProduct);
        session.persist(productToAdd);
        transaction.commit();
        System.out.println("Employee " + employeeToGetProduct + " was added to " + productToAdd);
    }
    // TODO metodas istraukti pirmiausia employee arba product su visom sasajom ir dadeti butent ten dar viena reikalinga ir tada sukist atgal
}
