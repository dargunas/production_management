package appProjectWorkingWithProduct.repository;

import appProjectWorkingWithProduct.connectionUtils.HibernateUtil;
import appProjectWorkingWithProduct.UserMenu;
import appProjectWorkingWithProduct.sqlClasses.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeRepository {
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void deleteByEmployeeId(Integer employeeId) {
        Transaction transaction = session.beginTransaction();
        session.delete(findByEmployeeId(employeeId));
        transaction.commit();
        System.out.println("Employee was deleted " + findByEmployeeId(employeeId));
    }


    public List<Employee> findAllEmployees() {
        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
        return employees;
    }


    public void printAllEmployee() {
        UserMenu userMenu = new UserMenu();
        List<Employee> employees = findAllEmployees();
        for (Employee employee : employees) {
            try {
                printEmployee(employee);
            } catch (NullPointerException ignored) {
            }
        }
        userMenu.menuForUser();
    }

    public void printEmployee(Employee employee) {
        String employeeFirstName;
        String employeeLastName;
        String employeeId;
        String employeeWorkShopName;
        String employeeStartingDate;
        employeeFirstName = employee.getFirstName();
        employeeLastName = employee.getLastName();
        employeeId = employee.getEmployeeId().toString();
        employeeWorkShopName = employee.getWorkShopName();
        employeeStartingDate = employee.getStartingDate();
        System.out.println();
        System.out.println(" Employee ID : " + employeeId);
        System.out.println(" Employee First name and Last name is : "
                + employeeFirstName + " and " + employeeLastName);
        System.out.println(" Employee starting date is " + employeeStartingDate + " Workshop name is " + employeeWorkShopName);
        System.out.println();
    }


    public void saveEmployee(Employee employee) {
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Employee was added ");
        System.out.println();
        printEmployee(employee);
    }


    public Employee findByEmployeeId(Integer employeeId) {
        return session.find(Employee.class, employeeId);
    }

}
