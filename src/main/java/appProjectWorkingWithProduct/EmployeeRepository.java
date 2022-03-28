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
}
