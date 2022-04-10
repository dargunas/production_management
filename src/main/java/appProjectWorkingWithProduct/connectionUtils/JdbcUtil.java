package appProjectWorkingWithProduct.connectionUtils;

import java.sql.*;

public class JdbcUtil {

    public static ResultSet RetrieveData() throws SQLException {

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306/individual_work?serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(mysqlUrl, "root", "1128");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from employee");
        return resultSet;
    }
}

