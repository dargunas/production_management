package appProjectWorkingWithProduct;


import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        EmployeeAndProductAssignment employeeAndProductAssignment = new EmployeeAndProductAssignment();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        ProductRepository productRepository = new ProductRepository();
        UserMenu userMenu = new UserMenu();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        userMenu.menuForUser();

        session.getTransaction().commit();

    }
}
