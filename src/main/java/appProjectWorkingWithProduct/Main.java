package appProjectWorkingWithProduct;


import appProjectWorkingWithProduct.connectionUtils.HibernateUtil;
import appProjectWorkingWithProduct.makeJsonFile.JsonFromMySqlEmployee;
import org.hibernate.Session;


public class Main {


    public static void main(String[] args) {
//TODO autentification
        UserMenu userMenu = new UserMenu();
        JsonFromMySqlEmployee jsonFromMySql = new JsonFromMySqlEmployee();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //userMenu.menuForUser();
        session.getTransaction().commit();
        jsonFromMySql.getDataFromDatabaseToJsonFile();
    }
}
