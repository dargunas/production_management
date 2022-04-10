package appProjectWorkingWithProduct.makeJsonFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static appProjectWorkingWithProduct.connectionUtils.JdbcUtil.RetrieveData;

public class JsonFromMySqlEmployee {

    public void getDataFromDatabaseToJsonFile() {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            ResultSet resultSet = RetrieveData();
            while (resultSet.next()) {
                JSONObject employeeRecord = new JSONObject();
                employeeRecord.put("employeeId", resultSet.getInt("employeeId"));
                employeeRecord.put("firstName", resultSet.getString("firstName"));
                employeeRecord.put("lastName", resultSet.getString("lastName"));
                employeeRecord.put("startingDate", resultSet.getString("startingDate"));
                employeeRecord.put("workShopName", resultSet.getString("workShopName"));
                jsonArray.add(employeeRecord);
            }
            jsonObject.put("Employees", jsonArray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/employees");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}