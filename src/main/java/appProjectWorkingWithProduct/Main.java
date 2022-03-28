package appProjectWorkingWithProduct;


import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        EmployeeAndProductAssignment employeeAndProductAssignment = new EmployeeAndProductAssignment();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        ProductRepository productRepository = new ProductRepository();

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

       // employeeAndProductAssignment.assignEmployeeWithProduct(1, 5L);
        employeeAndProductAssignment.assignEmployeeWithProduct(1, 7L);
        session.getTransaction().commit();

    }
}
