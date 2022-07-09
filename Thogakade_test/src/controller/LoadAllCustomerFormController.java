package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.sql.*;

public class LoadAllCustomerFormController {
    public TableView tblCustomer;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCusSalary;

    public void initialize() {

        colCusId.setCellValueFactory(new PropertyValueFactory("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory("cusName"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory("cusAddress"));
        colCusSalary.setCellValueFactory(new PropertyValueFactory("cusSalary"));

        LoadAllCustomer();
    }

    private void LoadAllCustomer() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            String sql = "SELECT * FROM Customer";
            Statement sta = con.createStatement();
            ResultSet resultSet = sta.executeQuery(sql);
            ObservableList<Customer> obList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                obList.add(
                        new Customer(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("address"),
                                resultSet.getDouble("salary")
                        )
                );
            }
            tblCustomer.setItems(obList);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
