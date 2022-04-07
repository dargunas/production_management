package appProjectWorkingWithProduct;

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
        List<Employee> employees = findAllEmployees();
        String employeeFirstName;
        String employeeLastName;
        String employeeId;
        String employeeWorkShopName;
        String employeeStartingDate;
        for (Employee employee : employees) {
            try {
                employeeFirstName = employee.getFirstName();
                employeeLastName = employee.getLastName();
                employeeId = employee.getEmployeeId().toString();
                employeeWorkShopName = employee.getWorkShopName();
                employeeStartingDate = employee.getStartingDate();
            } catch (NullPointerException e) {
                continue;
            }
            System.out.println();
            System.out.println(" Employee ID : " + employeeId);
            System.out.println(" Employee First name and Last name is : "
                    + employeeFirstName + " and " + employeeLastName);
            System.out.println(" Employee starting date is " + employeeStartingDate +  " Workshop name is " +  employeeWorkShopName);
            System.out.println();

        }
    }


    public void saveEmployee(Employee employee) {
        Transaction transaction = session.beginTransaction();
        session.persist(employee);
        transaction.commit();
        System.out.println("Employee was added " + employee);
    }


    public Employee findByEmployeeId(Integer employeeId) {
        return session.find(Employee.class, employeeId);
    }

//    public void printAllEmployees (R){
//        while (query.next()){
//
//        }
//    }
}
