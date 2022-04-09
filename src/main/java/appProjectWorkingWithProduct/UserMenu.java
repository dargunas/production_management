package appProjectWorkingWithProduct;

import appProjectWorkingWithProduct.repository.EmployeeRepository;
import appProjectWorkingWithProduct.repository.ProductRepository;
import appProjectWorkingWithProduct.sqlClasses.Employee;
import appProjectWorkingWithProduct.sqlClasses.Product;

import java.util.Scanner;

public class UserMenu {
    Scanner scanner = new Scanner(System.in);
    EmployeeAndProductAssignment employeeAndProductAssignment = new EmployeeAndProductAssignment();
    ProductRepository productRepository = new ProductRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    int userElection;

    public void menuForUser() {
        System.out.println(" <----------------------------------------Menu------------------------------------------>");
        System.out.println("    Modify employees owned products PRESS                                         -> 1 <-");
        System.out.println("    Add worked hours of product and mark product's state PRESS                    -> 2 <-");
        System.out.println("    Add employee PRESS                                                            -> 3 <-");
        System.out.println("    Add product PRESS                                                             -> 4 <-");
        System.out.println("    Delete Employee                                                               -> 5 <-");
        System.out.println("    Delete Product                                                                -> 6 <-");
        System.out.println("    Show all Employees                                                            -> 7 <-");
        System.out.println("    Show all Products                                                             -> 8 <-");
        System.out.println("    Exit menu PRESS                                                               -> 9 <-");
        System.out.println(" <-------------------------------------------------------------------------------------->");
        try {
            boolean isRunning = true;
            while (isRunning) {
                userElection = scanner.nextInt();
                if (userElection == 1) {
                    subMenuModifyEmployeesOwnedProducts();
                } else if (userElection == 2) {
                    subMenuForAddingWorkingHoursAndSettingProductAsReady();
                } else if (userElection == 3) {
                    subMenuForAddingNewEmployee();
                } else if (userElection == 4) {
                    subMenuForAddingNewProduct();
                } else if (userElection == 5) {
                    subMenuForDeletingEmployee();
                } else if (userElection == 6) {
                    subMenuForDeletingProduct();
                } else if (userElection == 7) {
                    employeeRepository.printAllEmployee();
                } else if (userElection == 8) {
                    productRepository.showAllProducts();
                } else if (userElection == 9) {
                    isRunning = false;
                } else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Enter valid integer!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    menuForUser();
                }
            }
        } catch (
                Exception exception){
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Wrong symbols was provided!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }

    }


    public void subMenuModifyEmployeesOwnedProducts() {

        System.out.println(" <---------------------------Enter Employees ID and products ID------------------------> ");
        System.out.println();
        System.out.println("                                  Enter employeeID : ");
        Integer employeeId = scanner.nextInt();
        System.out.println();
        System.out.println("                                  Enter productID : ");
        Long productId = scanner.nextLong();
        employeeAndProductAssignment.assignEmployeeWithProduct(employeeId, productId);
        menuForUser();
    }

    public void subMenuForAddingWorkedHoursToProduct() {

        System.out.println(" <---------------------------Enter product ID and worked hours-------------------------->");
        System.out.println();
        System.out.println("                          Enter product ID that was worked on:");
        Long productId = scanner.nextLong();
        System.out.println();
        System.out.println("                               Enter hours that was used: ");
        Integer usedHours = scanner.nextInt();
        productRepository.modifyUsedHoursForProduction(productId, usedHours);
        menuForUser();
    }

    public void subMenuForAddingNewEmployee() {

        System.out.println("<------------------- Add employees first name, last name, starting date----------------->");
        System.out.println();
        System.out.println("                             Enter employees first name: ");
        String firstName = scanner.next();
        System.out.println("                             Enter employees last name: ");
        String lastName = scanner.next();
        System.out.println("                               Enter starting date: ");
        String startingDate = scanner.next();
        System.out.println("                                Enter workshop name: ");
        String workShopName = scanner.next();
        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .startingDate(startingDate)
                .workShopName(workShopName)
                .build();
        employeeRepository.saveEmployee(employee);
        menuForUser();
    }

    public void subMenuForAddingNewProduct() {
        System.out.println("<----------------------------------------------------------------------------------------------------->");
        System.out.println("< Add product given hours for production, price of product, production end date, production start date)");
        System.out.println("<----------------------------------------------------------------------------------------------------->");
        System.out.println();
        System.out.println("                           Enter product given hours for production:");
        Integer productHours = scanner.nextInt();
        System.out.println("                                   Enter price of product: ");
        Double price = scanner.nextDouble();
        System.out.println("                               Enter estimated production end date:");
        String date = scanner.next();
        System.out.println("                                  Enter production start date: ");
        String dateStart = scanner.next();
        Product product = Product.builder()
                .givenHoursForProduction(productHours)
                .priceOfProduct(price).productionEndDate(date)
                .productionStartDate(dateStart)
                .hoursLeftForProduction(0)
                .hoursUsedForProduction(0)
                .isReady(0)
                .build();
        productRepository.saveProduct(product);
        menuForUser();
    }

    public void subMenuForDeletingEmployee() {

        System.out.println("<------------------- Write employee Id what you prefer to delete-----------------------> ");
        System.out.println();
        Integer employeeId = scanner.nextInt();
        employeeRepository.deleteByEmployeeId(employeeId);
        menuForUser();
    }

    public void subMenuForDeletingProduct() {
        System.out.println("<------------------------Enter product ID to delete a product--------------------------->");
        productRepository.deleteByProductId(scanner.nextLong());
        menuForUser();
    }

    public void subMenuForAddingWorkingHoursAndSettingProductAsReady() {
        System.out.println("<-------------------------------------SUB MENU------------------------------------------>");
        System.out.println("      Add hours press                                                             -> 1 <-");
        System.out.println("      Change product state press                                                  -> 2 <-");
        System.out.println("      To exit to main menu press                                                  -> 3 <-");
        userElection = scanner.nextInt();
        if (userElection == 1) {
            subMenuForAddingWorkedHoursToProduct();
        } else if (userElection == 2) {
            System.out.println("<--------------------------Enter product number------------------------------------->");
            productRepository.markProductWhenItIsReady(scanner.nextLong());
        } else if (userElection == 3) {
            menuForUser();
        } else {
            System.out.println("<----------------You have entered wrong number. Try again--------------------------->");
            subMenuForAddingWorkingHoursAndSettingProductAsReady();
        }
    }
}
