package appProjectWorkingWithProduct;


import org.hibernate.Session;


public class Main {


    public static void main(String[] args) {

        EmployeeAndProductAssignment employeeAndProductAssignment = new EmployeeAndProductAssignment();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        ProductRepository productRepository = new ProductRepository();
        UserMenu userMenu = new UserMenu();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //userMenu.menuForUser();
        //employeeAndProductAssignment.assignEmployeeWithProduct(1, 2L);
        //employeeRepository.deleteByEmployeeId1(2);
        // productRepository.deleteByProductId(2L);
        //productRepository.markProductWhenItIsReady(1L);
        //Product product = productRepository.findByProductId(1L);
        //System.out.println(product);
        //employeeRepository.findAllEmployees();
        employeeRepository.printAllEmployee();
        productRepository.showAllProducts();
        session.getTransaction().commit();

    }
}
