package appProjectWorkingWithProduct;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class ProductRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void deleteByProductNumber(Long productNumber) {
        Transaction transaction = session.beginTransaction();
        session.delete(findByProductNumber(productNumber));
        transaction.commit();
        System.out.println("Product was deleted " + findByProductNumber(productNumber));
    }

    public void saveProduct(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        System.out.println("Product was added " + product);
    }

    public Product findByProductNumber(Long productNumber) {
        return session.find(Product.class, productNumber);
    }

    public void modifyUsedHoursForProduction(Long productNumber, Integer hoursUsedForProductionToAdd) {
        Transaction transaction = session.beginTransaction();
        Product productToModify = findByProductNumber(productNumber);
        Integer spentHours = productToModify.getHoursUsedForProduction() + hoursUsedForProductionToAdd;
        Integer leftHours = productToModify.getHoursLeftForProduction() - hoursUsedForProductionToAdd;
        productToModify.setHoursUsedForProduction(spentHours);
        productToModify.setHoursLeftForProduction(leftHours);
        session.persist(productToModify);
        transaction.commit();
        System.out.println("hours was added");
    }
}
