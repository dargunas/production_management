package appProjectWorkingWithProduct.repository;

import appProjectWorkingWithProduct.connectionUtils.HibernateUtil;
import appProjectWorkingWithProduct.sqlClasses.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ProductRepository {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void deleteByProductId(Long productId) {
        Transaction transaction = session.beginTransaction();
        Product product = findByProductId(productId);
        if (product.getEmployees() != null) {
            product.getEmployees().forEach(employee -> employee.getProducts().remove(product));
            session.remove(product);
            transaction.commit();
        } else {
            session.delete(product);
            transaction.commit();
        }
        System.out.println("Employee was removed ");
    }

    public void saveProduct(Product product) {
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        System.out.println("Product was added " + product);
    }

    public Product findByProductId(Long productId) {
        return session.find(Product.class, productId);
    }

    public void modifyUsedHoursForProduction(Long productNumber, Integer hoursUsedForProductionToAdd) {
        Transaction transaction = session.beginTransaction();
        Product productToModify = findByProductId(productNumber);
        Integer spentHours = productToModify.getHoursUsedForProduction() + hoursUsedForProductionToAdd;
        Integer leftHours = productToModify.getHoursLeftForProduction() - hoursUsedForProductionToAdd;
        productToModify.setHoursUsedForProduction(spentHours);
        productToModify.setHoursLeftForProduction(leftHours);
        session.persist(productToModify);
        transaction.commit();
        System.out.println("hours was added");
    }

    public void markProductWhenItIsReady(Long productId) {
        Transaction transaction = session.beginTransaction();
        Product product = findByProductId(productId);
        product.setIsReady(1);
        session.update(product);
        transaction.commit();
        System.out.println();
        System.out.println(" <-----Product is Ready-----> ");
        System.out.println();
    }

    public boolean isProductReady(Long productId) {
        boolean isReady;
        if (findByProductId(productId).getIsReady().equals(1)) {
            isReady = true;
        } else {
            isReady = false;
        }
        return (isReady);
    }

    public boolean digitToBoolean(Integer digit){
        boolean isReady = false;
        if (digit == 1) {
            isReady = true;
        } else if (digit == 0) {
            isReady = false;
        }else{
            System.out.println(" Value of provided data is incorect !!!");
        }
        return isReady;
    }

    public List<Product> findAllProducts() {
        List<Product> products = session.createQuery("FROM Product", Product.class).list();
        return products;
    }

    public void showAllProducts() {
        List<Product> products = findAllProducts();
        String productID;
        String givenHoursForProduction;
        String usedHoursForProduction;
        String hoursLeftForProduction;
        boolean isReady;
        Double priceOfProduct;
        String productionEndDate;
        String productionStartDate;
        String flag;
        for (Product product : products) {
            try {
                productID = product.getProductId().toString();
                givenHoursForProduction = product.getGivenHoursForProduction().toString();
                usedHoursForProduction = product.getHoursUsedForProduction().toString();
                hoursLeftForProduction = product.getHoursLeftForProduction().toString();
                Integer isReadyFromProduct = product.getIsReady();
                isReady = digitToBoolean(isReadyFromProduct);
                priceOfProduct = product.getPriceOfProduct();
                productionEndDate = product.getProductionEndDate();
                productionStartDate = product.getProductionStartDate();
            } catch (NullPointerException e) {
                continue;
            }
            if (isReady){
                flag = "Product is ready for shipping";
            }else{
                flag = "Product is in production state";
            }
            System.out.println();
            System.out.println(" Product ID : " + productID + " product's state : " + flag);
            System.out.println();
            System.out.println(" Product's price is " + priceOfProduct);
            System.out.println(" Product's production hours is : " + givenHoursForProduction);
            System.out.println(" Product's used hours for production is : " + usedHoursForProduction);
            System.out.println(" Product's hours that are left for production is : " + hoursLeftForProduction);
            System.out.println(" Production start date is " + productionStartDate + " estimated end date is " + productionEndDate);
            System.out.println();

        }
    }

}
