package appProjectWorkingWithProduct;


import org.hibernate.Session;


public class Main {


    public static void main(String[] args) {
//TODO autentification
        UserMenu userMenu = new UserMenu();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        userMenu.menuForUser();
        session.getTransaction().commit();
    }
}
