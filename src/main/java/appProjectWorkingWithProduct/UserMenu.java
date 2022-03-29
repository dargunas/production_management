package appProjectWorkingWithProduct;

import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.Scanner;

public class UserMenu {
    Scanner scanner = new Scanner(System.in);
    public boolean isRunning = true;
    EmployeeAndProductAssignment employeeAndProductAssignment = new EmployeeAndProductAssignment();
    ProductRepository productRepository = new ProductRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();

    public void menuForUser() {

        //while (isRunning) {

        System.out.println(" Modify employees owned products PRESS -> 1");

        System.out.println(" Add worked hors of product PRESS -> 2");

        System.out.println(" Add employee PRESS -> 3");

        System.out.println(" Add product PRESS -> 4");

        System.out.println(" Exit menu PRESS -> 5");
        try {
            int userElection = scanner.nextInt();
            while (isRunning) {
                if (userElection == 1) {
                    subMenuModifyEmployeesOwnedProducts();
                } else if (userElection == 2) {
                    subMenuForAddingWorkedHoursToProduct();
                } else if (userElection == 3) {
                    subMenuForAddingNewEmployee();
                } else if (userElection == 4) {
                    subMenuForAddingNewProduct();
                } else if (userElection == 5) {
                    break;
                } else System.out.println("!!!!!!!!!!!!!!!!!Enter valid integer!!!!!!!!!!!!!!!!!!!!");
                menuForUser();
            }
        } catch (Exception exception) {
            System.out.println("Wrong symbols was provided!!!");
        }
    }


    public void subMenuModifyEmployeesOwnedProducts() {

        System.out.println(" Enter Employees ID and products ID ");
        System.out.println();
        System.out.println(" Enter employeeID : ");
        Integer employeeId = scanner.nextInt();
        System.out.println();
        System.out.println(" Enter productID : ");
        Long productId = scanner.nextLong();
        employeeAndProductAssignment.assignEmployeeWithProduct(employeeId, productId);

    }

    public void subMenuForAddingWorkedHoursToProduct() {

        System.out.println(" Enter product ID and worked hours ");
        System.out.println();
        System.out.println(" Enter product ID that was worked on:");
        Long productId = scanner.nextLong();
        System.out.println();
        System.out.println(" Enter hours that was used: ");
        Integer usedHours = scanner.nextInt();
        productRepository.modifyUsedHoursForProduction(productId, usedHours);
    }

    public void subMenuForAddingNewEmployee() {

        System.out.println(" Add employees first name, last name, starting date");
        System.out.println();
        System.out.println(" Enter employees first name: ");
        String firstName = scanner.next();
        System.out.println(" Enter employees last name: ");
        String lastName = scanner.next();
        System.out.println(" Enter starting date: ");
        String startingDate = scanner.next();
        System.out.println(" Enter workshop name: ");
        String workShopName = scanner.next();
        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .startingDate(startingDate)
                .workShopName(workShopName)
                .build();
        employeeRepository.saveEmployee(employee);

    }

    public void subMenuForAddingNewProduct() {

        System.out.println(" Add product given hours for production, price of product, production end date, production start date)");
        System.out.println();
        System.out.println("Enter product given hours for production:");
        Integer productHours = scanner.nextInt();
        System.out.println(" Enter price of product: ");
        Double price = scanner.nextDouble();
        System.out.println(" Enter production end date:");
        String date = scanner.next();
        System.out.println(" Enter production start date: ");
        String dateStart = scanner.next();
        Product product = Product.builder()
                .givenHoursForProduction(productHours)
                .priceOfProduct(price).productionEndDate(date)
                .productionStartDate(dateStart)
                .hoursLeftForProduction(0)
                .hoursUsedForProduction(0)
                .build();
        productRepository.saveProduct(product);
    }


}
