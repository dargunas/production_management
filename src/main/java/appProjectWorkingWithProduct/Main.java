package appProjectWorkingWithProduct;


import org.hibernate.Session;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Session session = HibernateUtil.getSessionFactory().openSession();

//        Product product12345 = Product.builder()
//                .hoursUsedForProduction(0)
//                .hoursLeftForProduction(50)
//                .givenHoursForProduction(70)
//                .productNumber(33331L)
//                .build();
//
//        Employee petras = Employee.builder()
//                .firstName("Petras")
//                .lastName("Pirdunas")
//                .startingDate("2018-10-05")
//                .workShopName("1")
//                .build();

        session.beginTransaction();

       // session.save(petras);

        //productRepository.modifyUsedHoursForProduction(33333L, 10);

        employeeRepository.addingProductToEmployee(2, 33332L);

//        Product daiktas = productRepository.findByProductNumber(33333L);
//        System.out.println("daiktas " + daiktas);
//        Employee byEmployeeId = employeeRepository.findByEmployeeId(1);
//        System.out.println(byEmployeeId);
//        productRepository.addingEmployeeToProduct(33331L, 3);


        session.getTransaction().commit();
    }
}
