package appProjectWorkingWithProduct;


import org.hibernate.Session;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();
        Session session = HibernateUtil.getSessionFactory().openSession();

//        Product product33333 = Product.builder()
//                .hoursUsedForProduction(0)
//                .hoursLeftForProduction(50)
//                .givenHoursForProduction(70)
//                .productNumber(33330L)
//                .build();

        session.beginTransaction();

        // session.save(product33333);

        productRepository.modifyUsedHoursForProduction(33333L, 10);


//        Product daiktas = productRepository.findByProductNumber(33333L);
//        System.out.println("daiktas " + daiktas);


        session.getTransaction().commit();
    }
}
