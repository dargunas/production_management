package appProjectWorkingWithProduct;

import org.hibernate.Session;

public class ProductService {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    // TODO issiaiskinti kaip sukisti productNumber nehardkodinant, greiciausiai JavaFx ar dar kas....

    //TODO getHourUsedForProduction reikia parasyti metoda, kuris paimtu duotas valandas is vartotojo ir jas updatintu duombazeje!!!

}
