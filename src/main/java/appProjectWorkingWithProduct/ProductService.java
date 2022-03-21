package appProjectWorkingWithProduct;

import org.hibernate.Session;

public class ProductService {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    // TODO issiaiskinti kaip sukisti productNumber nehardkodinant, greiciausiai JavaFx ar dar kas....
    public void calculationOfUsedTime() {

        Product product = new Product();
        ProductRepository productRepository = new ProductRepository();

        productRepository.findByProductNumber(36562L);
        product.setHoursLeftForProduction(product.getGivenHoursForProduction() - product.getHoursUsedForProduction());


    //TODO getHourUsedForProduction reikia parasyti metoda, kuris paimtu duotas valandas is vartotojo ir jas updatintu duombazeje!!!


    }
}
